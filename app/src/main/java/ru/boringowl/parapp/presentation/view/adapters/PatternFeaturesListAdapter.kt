package ru.boringowl.parapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternFeatureListItemBinding
import ru.boringowl.parapp.databinding.PatternsListItemBinding
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

class PatternFeaturesListAdapter(var data : List<PatternFeature>) :
    RecyclerView.Adapter<PatternFeaturesListAdapter.PatternFeatureViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatternFeatureViewHolder {
        val binding: PatternFeatureListItemBinding =
            PatternFeatureListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        return PatternFeatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatternFeatureViewHolder, position: Int) {
        val feature = data[position]
        holder.binding.featureTitle.text = feature.title
        val imageRes = if (feature.isAdvantage)
            R.drawable.ic_round_add_box_24
        else
            R.drawable.ic_round_indeterminate_check_box_24
        holder.binding.imageViewFeature.setImageResource(imageRes)
    }


    class PatternFeatureViewHolder(val binding: PatternFeatureListItemBinding) : RecyclerView.ViewHolder(binding.root)
}