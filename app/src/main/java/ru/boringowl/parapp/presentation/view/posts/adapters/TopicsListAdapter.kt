package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.PostListItemBinding
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.view.posts.PostsListFragmentDirections
import android.widget.TextView
import ru.boringowl.parapp.R
import android.widget.RelativeLayout
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import ru.boringowl.parapp.databinding.TopicItemBinding
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.utils.ImageUtils
import ru.boringowl.parapp.presentation.view.posts.TopicsFragmentDirections
import java.lang.Exception


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
            val action = TopicsFragmentDirections.actionTopicsFragmentToNotesListFragment(topic.id!!)
            action.let { act -> it.findNavController().navigate(act) }
        }
    }

    class TopicViewHolder(val binding: TopicItemBinding) : RecyclerView.ViewHolder(binding.root)
}