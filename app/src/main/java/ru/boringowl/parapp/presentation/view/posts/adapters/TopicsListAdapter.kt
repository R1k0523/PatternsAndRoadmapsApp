package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.TopicItemBinding
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.view.posts.TopicsFragmentDirections


class TopicsListAdapter(var data: List<Topic>) :
    RecyclerView.Adapter<TopicsListAdapter.TopicViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicViewHolder {
        val binding: TopicItemBinding =
            TopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = data[position]
        holder.binding.title.text = topic.title
        holder.binding.root.setOnClickListener {
            val action = TopicsFragmentDirections.actionTopicsFragmentToNotesListFragment(topic.topicId!!.toString())
            action.let { act -> it.findNavController().navigate(act) }
        }
    }

    class TopicViewHolder(val binding: TopicItemBinding) : RecyclerView.ViewHolder(binding.root)
}