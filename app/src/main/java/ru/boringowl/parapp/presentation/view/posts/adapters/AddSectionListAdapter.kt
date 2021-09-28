package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.AddSectionListItemBinding
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection

class AddSectionListAdapter(private val viewLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<AddSectionListAdapter.AddSectionViewHolder>() {
    var data = arrayListOf<NoteSection>()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddSectionViewHolder {
        val binding: AddSectionListItemBinding =
            AddSectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddSectionViewHolder, position: Int) {
        val section = data[position]
        holder.binding.section = section
        holder.binding.sectionsRecyclerView.also {
            it.layoutManager = LinearLayoutManager(holder.binding.root.context)
            it.setHasFixedSize(false)
            it.adapter = AddLinkListAdapter()
            (it.adapter as AddLinkListAdapter).data.observe(viewLifecycleOwner,{links ->
                data[position].linkItems = links
            })
        }
        holder.binding.addLink.setOnClickListener {
            (holder.binding.sectionsRecyclerView.adapter as AddLinkListAdapter).addField()
        }
        holder.binding.description.addTextChangedListener {
            data[position].description = it.toString()
        }
    }

    fun addSection() {
        data.add(NoteSection("", arrayListOf()))
        notifyItemInserted(data.size-1)
    }

    class AddSectionViewHolder(val binding: AddSectionListItemBinding) : RecyclerView.ViewHolder(binding.root)
}