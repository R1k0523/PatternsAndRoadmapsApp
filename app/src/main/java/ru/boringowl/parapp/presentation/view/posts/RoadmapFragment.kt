package ru.boringowl.parapp.presentation.view.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.boringowl.parapp.R
import ru.boringowl.parapp.presentation.viewmodel.posts.RoadmapViewModel

class RoadmapFragment : Fragment() {

    companion object {
        fun newInstance() = RoadmapFragment()
    }

    private lateinit var viewModel: RoadmapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.roadmap_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoadmapViewModel::class.java)
        // TODO: Use the ViewModel
    }

}