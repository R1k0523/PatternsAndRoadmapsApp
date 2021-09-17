package ru.boringowl.parapp.presentation.view

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
import ru.boringowl.parapp.databinding.PatternFragmentBinding
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.view.adapters.PatternFeaturesListAdapter
import ru.boringowl.parapp.presentation.viewmodel.PatternViewModel
import ru.boringowl.parapp.presentation.viewmodel.factory.PatternViewModelFactory

class PatternFragment : Fragment() {

    companion object {
        fun newInstance() = PatternFragment()
    }
    private val args: PatternFragmentArgs by navArgs()
    private val viewModel: PatternViewModel by viewModels { PatternViewModelFactory(args.patternId) }
    private lateinit var binding : PatternFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PatternFragmentBinding.inflate(layoutInflater, container, false)
        viewModel.patternInfo.observe(viewLifecycleOwner, {
            binding.pattern = it
            val patternRes = when(it.type) {
                PatternInfo.PatternType.STRUCTURAL -> R.drawable.ic_round_table_rows_24
                PatternInfo.PatternType.CREATIONAL -> R.drawable.ic_round_add_box_24
                else -> R.drawable.ic_round_swap_calls_24
            }
            binding.patternTypeImage.setImageResource(patternRes)
            val difficultyRes = when(it.difficulty) {
                0 -> R.drawable.ic_round_sentiment_satisfied_alt_24
                2 -> R.drawable.ic_round_sentiment_dissatisfied_24
                else -> R.drawable.ic_round_sentiment_neutral_24
            }
            binding.difficultyImage.setImageResource(difficultyRes)
        })
        return binding.root
    }



}
@BindingAdapter("app:features", "app:context")
fun setRecycler(recyclerView: RecyclerView, features: List<PatternFeature>, context: Context) {
    recyclerView.also {
        it.layoutManager= LinearLayoutManager(context)
        it.setHasFixedSize(true)
        it.adapter =  PatternFeaturesListAdapter(features)
    }
}