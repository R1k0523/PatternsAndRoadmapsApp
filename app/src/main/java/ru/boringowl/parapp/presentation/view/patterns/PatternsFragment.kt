package ru.boringowl.parapp.presentation.view.patterns

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.PatternsFragmentBinding
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.mock.PatternsMockRepository
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.patterns.PatternsViewModel
import kotlin.random.Random

class PatternsFragment : Fragment() {

    private lateinit var viewModel: PatternsViewModel
    private lateinit var binding : PatternsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PatternsFragmentBinding.inflate(layoutInflater, container, false)
        binding.patternsRecyclerView.layoutManager = LinearLayoutManager(context)

        if (Repository.currentUser.value != null) {
            if (Repository.currentUser.value!!.role == User.Roles.MODERATOR ||
                Repository.currentUser.value!!.role == User.Roles.ADMIN
            ) {
                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.absoluteAdapterPosition
                        viewModel.deletePatternInfo(
                            (binding.patternsRecyclerView.adapter as PatternsListAdapter).data[position]
                        )
                    }
                }).attachToRecyclerView(binding.patternsRecyclerView)
                binding.floatingActionButton2.setOnClickListener {
                    viewModel.addPatternInfo(
                        PatternsMockRepository().listMock[Random.nextInt(
                            0,
                            2
                        )]
                    )
                }
            } else {
            binding.floatingActionButton2.isVisible = false
        }
        } else {
            binding.floatingActionButton2.isVisible = false
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatternsViewModel::class.java)

        viewModel.getPatternsList().observe(viewLifecycleOwner, {
            binding.patternsRecyclerView.adapter = PatternsListAdapter(it!!)
        })

    }
}