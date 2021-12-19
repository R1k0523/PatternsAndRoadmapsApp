package ru.boringowl.parapp.presentation.view.posts

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
import ru.boringowl.parapp.databinding.TopicsFragmentBinding
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.view.posts.adapters.TopicsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.posts.TopicsViewModel
import kotlin.random.Random.Default.nextInt

class TopicsFragment : Fragment() {

    private lateinit var viewModel: TopicsViewModel
    private lateinit var binding: TopicsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TopicsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopicsViewModel::class.java)
        binding.recycler.layoutManager= LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)
        viewModel.getTopicsList().observe(viewLifecycleOwner, {
            binding.recycler.adapter = TopicsListAdapter(it)
        })
        if (Repository.currentUser.value != null) {
            if (Repository.currentUser.value!!.role == User.Roles.MODERATOR ||
                Repository.currentUser.value!!.role == User.Roles.ADMIN) {
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
                        viewModel.deleteTopicInfo(
                            (binding.recycler.adapter as TopicsListAdapter).data[position]
                        )
                    }
                }).attachToRecyclerView(binding.recycler)
            }
            binding.floatingActionButton3.isVisible = true
            binding.floatingActionButton3.setOnClickListener {
                viewModel.addTopicInfo(
                    Topic(
                        topicId = null,
                        title = "Топик №${nextInt(0, 500)}",
                        creator = Repository.currentUser.value!!,
                        moderators = listOf()
                    )
                )
            }
        } else {
            binding.floatingActionButton3.isVisible = false
        }
    }
}
