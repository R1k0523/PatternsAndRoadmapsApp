package ru.boringowl.parapp.presentation.view.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dev.bandb.graphview.AbstractGraphAdapter
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.tree.BuchheimWalkerConfiguration
import dev.bandb.graphview.layouts.tree.BuchheimWalkerLayoutManager
import dev.bandb.graphview.layouts.tree.TreeEdgeDecoration
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.RoadmapFragmentBinding
import ru.boringowl.parapp.presentation.view.patterns.PatternFragmentArgs
import ru.boringowl.parapp.presentation.view.posts.adapters.RoadmapTreeAdapter
import ru.boringowl.parapp.presentation.viewmodel.factory.PatternViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.factory.RoadmapViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.patterns.PatternViewModel
import ru.boringowl.parapp.presentation.viewmodel.posts.RoadmapViewModel




class RoadmapFragment : Fragment() {

    companion object {
        fun newInstance() = RoadmapFragment()
    }

    private val args: RoadmapFragmentArgs by navArgs()
    private val viewModel: RoadmapViewModel by viewModels { RoadmapViewModelFactory(args.roadmapId) }
    private lateinit var binding: RoadmapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RoadmapFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val configuration = BuchheimWalkerConfiguration.Builder()
            .setSiblingSeparation(100)
            .setLevelSeparation(100)
            .setSubtreeSeparation(100)
            .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
            .build()
        binding.recycler.layoutManager = BuchheimWalkerLayoutManager(requireContext(), configuration)

        binding.recycler.addItemDecoration(TreeEdgeDecoration())
        viewModel.roadmap.observe(viewLifecycleOwner, {
            binding.roadmap = it
            val adapter = RoadmapTreeAdapter(it.root)
            adapter.drawTree()
            binding.recycler.adapter = adapter
        })
    }

}