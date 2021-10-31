package ru.boringowl.parapp.presentation.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PostsListFragmentBinding
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.mock.NotesMockRepository
import ru.boringowl.parapp.presentation.repository.mock.RoadmapsMockRepository
import ru.boringowl.parapp.presentation.view.posts.adapters.PostsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.factory.NoteViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.factory.PostsViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel
import ru.boringowl.parapp.presentation.viewmodel.posts.PostsListViewModel
import java.util.*


class PostsListFragment : Fragment() {

    private val args: PostsListFragmentArgs by navArgs()
    private val viewModel: PostsListViewModel by viewModels { PostsViewModelFactory(args.topicId) }
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
        binding.fabNote.setOnLongClickListener {
            viewModel.addNote(NotesMockRepository().listMock[Random().nextInt(3)])
            true
        }
        binding.fabRoadmap.setOnLongClickListener {
            viewModel.addRoadmap(RoadmapsMockRepository().listMock[Random().nextInt(1)])
            true
        }
        binding.fabNote.setOnClickListener {
            findNavController().navigate(PostsListFragmentDirections.actionNotesListFragmentToAddNoteFragment(args.topicId))
        }

        binding.fabRoadmap.setOnClickListener {
            findNavController().navigate(PostsListFragmentDirections.actionNotesListFragmentToAddRoadmapFragment(args.topicId))
        }

        if (Repository.currentUser.value != null) {
            if (Repository.currentUser.value!!.role == User.Roles.MODERATOR ||
                Repository.currentUser.value!!.role == User.Roles.ADMIN ||
                Repository.currentUser.value!!.email == viewModel.topic.creator) {
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
            } else {
                binding.fabRoadmap.isVisible = false
                binding.floatingActionButton.isVisible = false
                binding.fabNote.isVisible = false
            }
        } else {
            binding.fabRoadmap.isVisible = false
            binding.floatingActionButton.isVisible = false
            binding.fabNote.isVisible = false
        }
    }

    private fun showFABMenu() {
        binding.fabNote.animate().translationY(-resources.getDimension(R.dimen.standard_140))
        binding.fabRoadmap.animate().translationY(-resources.getDimension(R.dimen.standard_70))
    }

    private fun closeFABMenu() {
        binding.fabNote.animate().translationY(0F)
        binding.fabRoadmap.animate().translationY(0F)
    }

}