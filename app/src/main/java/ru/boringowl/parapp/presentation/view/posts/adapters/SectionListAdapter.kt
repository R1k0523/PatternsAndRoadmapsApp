package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsListItemBinding
import ru.boringowl.parapp.databinding.PostListItemBinding
import ru.boringowl.parapp.databinding.SectionListItemBinding
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.view.patterns.PatternsFragmentDirections
import ru.boringowl.parapp.presentation.view.patterns.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.view.posts.PostsListFragmentDirections

class SectionListAdapter(var data : List<NoteSection>) :
    RecyclerView.Adapter<SectionListAdapter.SectionViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SectionViewHolder {
        val binding: SectionListItemBinding =
            SectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = data[position]
        holder.binding.section = section
        holder.binding.root.setOnClickListener {
            val rvVisibility = holder.binding.sectionsRecyclerView.visibility
            holder.binding.sectionsRecyclerView.visibility =
                if(rvVisibility == View.VISIBLE)
                    View.GONE
                else
                    View.VISIBLE
        }
    }


    class SectionViewHolder(val binding: SectionListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
@BindingAdapter("app:sections", "app:context")
fun setRecyclerSections(recyclerView: RecyclerView, sections: List<NoteSection>, context: Context) {
    recyclerView.also {
        it.layoutManager= LinearLayoutManager(context)
        it.setHasFixedSize(true)
        it.adapter =  SectionListAdapter(sections)
    }
}
@BindingAdapter("app:link_items", "app:context")
fun setRecyclerItems(recyclerView: RecyclerView, linkItems: List<String>, context: Context) {
    recyclerView.also {
        it.layoutManager= LinearLayoutManager(context)
        it.setHasFixedSize(true)
        it.adapter =  LinkListAdapter(linkItems)
    }
}