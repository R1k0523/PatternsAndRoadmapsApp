package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.DocItemBinding


class DocsListAdapter(var isEditable: Boolean, var uriStrings : List<String>? = null) :
    RecyclerView.Adapter<DocsListAdapter.DocViewHolder>() {
    var data : ArrayList<Uri> = arrayListOf()

    init {
        uriStrings?.forEach {
            data.add(Uri.parse(it))
        }
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
        Log.d("Uri name", uri.toString())
        holder.binding.text.text = "Файл $position"
        if (isEditable) {
            holder.binding.root.setOnLongClickListener {
                data -= data[position]
                notifyDataSetChanged()
                true
            }
        }
        holder.binding.root.setOnClickListener {
            val fd = holder.binding.root.context.contentResolver.openOutputStream(uri, "r")
            val fileIntent = Intent(Intent.ACTION_VIEW, uri)
            //TODO ContentResolverUriToFileUri
            holder.binding.root.context.startActivity(fileIntent)
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
            it.adapter = DocsListAdapter(false, docItems)
        }
    }
}