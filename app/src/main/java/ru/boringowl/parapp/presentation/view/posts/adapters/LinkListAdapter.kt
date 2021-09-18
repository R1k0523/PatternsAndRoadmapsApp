package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.LinkListItemBinding

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