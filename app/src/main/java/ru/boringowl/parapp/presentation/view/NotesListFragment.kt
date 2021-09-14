package ru.boringowl.parapp.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.boringowl.parapp.R
import ru.boringowl.parapp.presentation.viewmodel.NotesListViewModel

class NotesListFragment : Fragment() {

    companion object {
        fun newInstance() = NotesListFragment()
    }

    private lateinit var viewModel: NotesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotesListViewModel::class.java)
    }

}