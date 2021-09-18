package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsListItemBinding
import ru.boringowl.parapp.databinding.PostListItemBinding
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.view.patterns.PatternsFragmentDirections
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.view.posts.PostsListFragmentDirections

class PostsListAdapter(var data : List<Post>) :
    RecyclerView.Adapter<PostsListAdapter.PostsViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsViewHolder {
        val binding: PostListItemBinding =
            PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = data[position]
        holder.binding.post = post
        holder.binding.root.setOnClickListener {
            val action = when (post) {
                is Note -> PostsListFragmentDirections
                        .actionNotesListFragmentToNoteFragment(post.id!!)
                is Roadmap -> PatternsFragmentDirections
                        .actionPatternsFragmentToPatternFragment(post.id)
                else -> null
            }
            action?.let { act -> it.findNavController().navigate(act) }
        }
    }


    class PostsViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}