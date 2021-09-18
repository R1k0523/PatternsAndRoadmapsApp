package ru.boringowl.parapp.presentation.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PostsListFragmentBinding
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.presentation.repository.mock.PostsMockBase
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.PostsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.posts.PostsListViewModel
import java.text.SimpleDateFormat
import java.util.*


class PostsListFragment : Fragment() {

    private lateinit var viewModel: PostsListViewModel
    private lateinit var binding: PostsListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostsListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsListViewModel::class.java)
        viewModel.isFABOpen.observe(viewLifecycleOwner, {
            if (it) {
                showFABMenu()
            } else {
                closeFABMenu()
            }
            binding.fabNote.isClickable = it
            binding.fabRoadmap.isClickable = it
        })

        binding.floatingActionButton.setOnClickListener {
            if (viewModel.isFABOpen.value == true)
                viewModel.setFABClosed()
            else
                viewModel.setFABOpen()
        }
        binding.recyclerView.layoutManager= LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        viewModel.getPostsList().observe(viewLifecycleOwner, {
            binding.recyclerView.adapter = PostsListAdapter(it)
        })
        binding.fabNote.setOnClickListener {
            viewModel.addNote(PostsMockBase().list[Random().nextInt(3)])
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deletePost(
                    (binding.recyclerView.adapter as PostsListAdapter).data[position]
                )
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

    private fun showFABMenu() {
        binding.fabNote.animate().translationY(-resources.getDimension(R.dimen.standard_140))
        binding.fabRoadmap.animate().translationY(-resources.getDimension(R.dimen.standard_70))
    }

    private fun closeFABMenu() {
        binding.fabNote.animate().translationY(0F)
        binding.fabRoadmap.animate().translationY(0F)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}