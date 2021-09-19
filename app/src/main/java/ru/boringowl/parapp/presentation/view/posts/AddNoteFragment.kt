package ru.boringowl.parapp.presentation.view.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.AddNoteFragmentBinding
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.view.posts.adapters.AddLinkListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.AddSectionListAdapter
import ru.boringowl.parapp.presentation.viewmodel.posts.AddNoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var viewModel: AddNoteViewModel
    private lateinit var binding: AddNoteFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(false)
            it.adapter =  AddSectionListAdapter(viewLifecycleOwner)
        }
        binding.button.setOnClickListener {
            (binding.recyclerView.adapter as AddSectionListAdapter).addSection()

        }
        binding.saveButton.setOnClickListener {
            val currentDateTime = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date())
            if (binding.title.text.isNotEmpty() && binding.description.text.isNotEmpty() && binding.category.text.isNotEmpty()) {
                viewModel.save(
                    Note(
                        title = binding.title.text.toString(),
                        postDescription = binding.description.text.toString(),
                        publicationDateTime = currentDateTime,
                        postCategories = binding.category.text.split(" "),
                        sections = (binding.recyclerView.adapter as AddSectionListAdapter).data,
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
    }

}