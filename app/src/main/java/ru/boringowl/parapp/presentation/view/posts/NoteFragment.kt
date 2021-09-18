package ru.boringowl.parapp.presentation.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.boringowl.parapp.databinding.NoteFragmentBinding
import ru.boringowl.parapp.presentation.viewmodel.factory.NoteViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel

class NoteFragment : Fragment() {

    companion object;

    private val args: NoteFragmentArgs by navArgs()
    private val viewModel: NoteViewModel by viewModels {NoteViewModelFactory(args.noteId)}
    private lateinit var binding: NoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.note.observe(viewLifecycleOwner, {
            binding.note = it
        })
    }
}

