package ru.boringowl.parapp.presentation.view.posts

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.NoteFragmentBinding
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternFeaturesListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.LinkListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.SectionListAdapter
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

