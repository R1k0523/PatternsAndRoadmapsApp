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
import java.lang.Exception


class PostsListAdapter(var data: List<Post>) :
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
                is Roadmap -> PostsListFragmentDirections
                        .actionNotesListFragmentToRoadmapFragment(post.id!!)
                else -> null
            }
            action?.let { act -> it.findNavController().navigate(act) }
        }
        post.postCategories.forEach { text ->
            val textview = TextView(holder.binding.root.context).also {
                it.text = text
                it.textSize = 12f
                it.minEms = 5
                it.gravity = Gravity.CENTER
                it.setPadding(8, 8, 8, 8)
                it.setBackgroundResource(R.drawable.category_shape)
                it.setTextColor(Color.BLACK)
                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(8, 0, 8, 0)
                it.layoutParams = params
            }
            holder.binding.categories.addView(textview)
        }
        if (post.image != null) {
            try {
                holder.binding.mainImage.setImageBitmap(
                    BitmapFactory.decodeFileDescriptor(
                        holder.binding.root.context.contentResolver.openFileDescriptor(
                            Uri.parse(post.image), "r"
                        )?.fileDescriptor
                    )
                )
                holder.binding.mainImage.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val postType = if (post is Note) "notes" else "roadmaps"
        holder.binding.shareImage.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://parapp.jun/$postType/${post.id}"
            )
            sendIntent.type = "text/plain"
            val shareIntent: Intent = Intent.createChooser(sendIntent, null)
            holder.binding.root.context.startActivity(shareIntent)
        }
    }


    class PostsViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}