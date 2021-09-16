package ru.boringowl.parapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsListItemBinding
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

class PatternsListAdapter(private var data : List<PatternInfo>) :
    RecyclerView.Adapter<PatternsListAdapter.PatternsViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatternsViewHolder {
        val binding: PatternsListItemBinding =
            PatternsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatternsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatternsViewHolder, position: Int) {
        val pattern = data[position]

        val patternRes = when(pattern.type) {
            PatternInfo.PatternType.STRUCTURAL -> R.drawable.ic_round_table_rows_24
            PatternInfo.PatternType.CREATIONAL -> R.drawable.ic_round_add_box_24
            else -> R.drawable.ic_round_swap_calls_24
        }
        holder.binding.patternTypeImage.setImageResource(patternRes)
        val difficultyRes = when(pattern.difficulty) {
            0 -> R.drawable.ic_round_sentiment_satisfied_alt_24
            2 -> R.drawable.ic_round_sentiment_dissatisfied_24
            else -> R.drawable.ic_round_sentiment_neutral_24
        }
        holder.binding.difficultyImage.setImageResource(difficultyRes)
    }

    class PatternsViewHolder(val binding: PatternsListItemBinding) : RecyclerView.ViewHolder(binding.root)
}