package ru.boringowl.parapp.presentation.view.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.NotesListFragmentBinding
import ru.boringowl.parapp.presentation.viewmodel.notes.NotesListViewModel


class NotesListFragment : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var binding: NotesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotesListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotesListViewModel::class.java)
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