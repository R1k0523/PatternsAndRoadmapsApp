package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.DocItemBinding
import ru.boringowl.parapp.presentation.utils.FileUtils


class DocsListAdapter(var isEditable: Boolean, var uriStrings : List<String>? = null, context: Context) :
    RecyclerView.Adapter<DocsListAdapter.DocViewHolder>() {
    var data : ArrayList<Uri> = arrayListOf()

    init {
        data = FileUtils.stringsToUriList(uriStrings, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DocViewHolder {
        val binding: DocItemBinding =
            DocItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DocViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocViewHolder, position: Int) {
        val uri = data[position]
        holder.binding.text.text = FileUtils.getFileName(holder.binding.root.context, uri)
        if (isEditable) {
            holder.binding.root.setOnLongClickListener {
                data -= data[position]
                notifyDataSetChanged()
                true
            }
        }
        holder.binding.root.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "*/*"
            }
            holder.binding.root.context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
        }
    }



    fun dataToString(): List<String> {
        val lists = arrayListOf<String>()
        data.forEach {
            lists.add(it.toString())
        }
        return lists
    }

    class DocViewHolder(val binding: DocItemBinding) : RecyclerView.ViewHolder(binding.root)
}

@BindingAdapter("app:doc_items", "app:context")
fun setRecyclerDocs(recyclerView: RecyclerView, docItems: List<String>?, context: Context) {
    if (docItems != null) {
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = DocsListAdapter(false, docItems, context)
        }
    }
}