package ru.boringowl.parapp.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsFragmentBinding
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.view.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.PatternsViewModel

class PatternsFragment : Fragment() {

    companion object {
        fun newInstance() = PatternsFragment()
    }

    private lateinit var viewModel: PatternsViewModel
    private lateinit var binding : PatternsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PatternsFragmentBinding.inflate(layoutInflater, container, false)
        binding.patternsRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatternsViewModel::class.java)

        viewModel.getPatternsList()!!.observe(viewLifecycleOwner, {
            Log.d("Patterns", viewModel.getPatternsList()!!.value!!.size.toString())
            binding.patternsRecyclerView.adapter = PatternsListAdapter(it!!)
        })

    }
}