package ru.boringowl.parapp.presentation.view.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.boringowl.parapp.databinding.NewsListItemBinding
import ru.boringowl.parapp.domain.model.news.Post


class NewsAdapter(val context: Context)
    : PagingDataAdapter<Post, NewsAdapter.PostViewHolder>(
    MovieModelComparator
) {
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post: Post? = getItem(position)
        if (post != null) {
            holder.binding.newspost = post
            if (post.urlToImage != null)
                Picasso.with(context).load(post.urlToImage).into(holder.binding.mainImage)
            else
                holder.binding.mainImage.isVisible = false
            holder.binding.root.setOnClickListener {
                val isOpened = holder.binding.isOpened
                holder.binding.isOpened = isOpened != true
            }
            holder.binding.shareImage.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(post.url))
                context.startActivity(browserIntent)
            }
        }
    }

    class PostViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: NewsListItemBinding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onViewRecycled(holder: PostViewHolder) {
        super.onViewRecycled(holder)
        holder.binding.mainImage.setImageBitmap(null)
        holder.binding.mainImage.isVisible = true
    }

    
    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return (oldItem.url == newItem.url)
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}