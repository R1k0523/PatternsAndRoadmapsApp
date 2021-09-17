package ru.boringowl.parapp.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsFragmentBinding
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.view.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.PatternsViewModel

class PatternsFragment : Fragment() {

    private lateinit var viewModel: PatternsViewModel
    private lateinit var binding : PatternsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PatternsFragmentBinding.inflate(layoutInflater, container, false)
        binding.patternsRecyclerView.layoutManager = LinearLayoutManager(context)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deletePatternInfo(
                    (binding.patternsRecyclerView.adapter as PatternsListAdapter).data[position]
                )
            }
        }).attachToRecyclerView(binding.patternsRecyclerView)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatternsViewModel::class.java)

        viewModel.getPatternsList()!!.observe(viewLifecycleOwner, {
            binding.patternsRecyclerView.adapter = PatternsListAdapter(it!!)
        })

    }
}