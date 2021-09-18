package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.LinkListItemBinding
import ru.boringowl.parapp.databinding.PatternsListItemBinding
import ru.boringowl.parapp.databinding.PostListItemBinding
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.view.patterns.PatternsFragmentDirections
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.view.posts.PostsListFragmentDirections

class LinkListAdapter(var data : List<String>) :
    RecyclerView.Adapter<LinkListAdapter.LinkViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LinkViewHolder {
        val binding: LinkListItemBinding =
            LinkListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val text = data[position]
        holder.binding.text.text = text
    }


    class LinkViewHolder(val binding: LinkListItemBinding) : RecyclerView.ViewHolder(binding.root)
}