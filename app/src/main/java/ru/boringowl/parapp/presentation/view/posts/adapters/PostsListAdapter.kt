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
import ru.boringowl.parapp.presentation.view.posts.PostsListFragmentDirections
import android.widget.TextView
import ru.boringowl.parapp.R
import android.widget.RelativeLayout
import android.net.Uri
import android.view.View
import ru.boringowl.parapp.presentation.utils.ImageUtils
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
            val action = when (post.isNote) {
                true -> PostsListFragmentDirections
                        .actionNotesListFragmentToNoteFragment(post.postId!!.toString())
                false -> PostsListFragmentDirections
                        .actionNotesListFragmentToRoadmapFragment(post.postId!!.toString())
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
                    ImageUtils.uriToBitmap(holder.binding.root.context, Uri.parse(post.image))
                )
                holder.binding.mainImage.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val postType = if (post.isNote) "notes" else "roadmaps"
        holder.binding.shareImage.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://parapp.jun/$postType/${post.postId}"
            )
            sendIntent.type = "text/plain"
            val shareIntent: Intent = Intent.createChooser(sendIntent, null)
            holder.binding.root.context.startActivity(shareIntent)
        }
    }


    override fun onViewRecycled(holder: PostsViewHolder) {
        super.onViewRecycled(holder)
        holder.binding.mainImage.visibility = View.GONE
        holder.binding.mainImage.setImageBitmap(null)
        holder.binding.categories.removeAllViews()
    }

    class PostsViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}