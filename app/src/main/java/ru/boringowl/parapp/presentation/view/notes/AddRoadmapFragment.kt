package ru.boringowl.parapp.presentation.view.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.boringowl.parapp.R
import ru.boringowl.parapp.presentation.viewmodel.notes.AddRoadmapViewModel

class AddRoadmapFragment : Fragment() {

    private lateinit var viewModel: AddRoadmapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_roadmap_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRoadmapViewModel::class.java)
    }

}