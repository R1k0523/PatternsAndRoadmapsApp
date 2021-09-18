package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.AddLinkListItemBinding

class AddLinkListAdapter() :
    RecyclerView.Adapter<AddLinkListAdapter.AddLinkViewHolder>() {
    private val _data = MutableLiveData<ArrayList<String>>()
    val data: LiveData<ArrayList<String>>
        get() = _data

    init {
        _data.value = arrayListOf()
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddLinkViewHolder {
        val binding: AddLinkListItemBinding =
            AddLinkListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddLinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddLinkViewHolder, position: Int) {
        val text = _data.value!![position]
        holder.binding.text.setText(text)
        holder.binding.text.addTextChangedListener {
            _data.value?.set(position, it.toString())
        }
    }

    fun addField() {
        _data.value!!.add("")
        notifyItemInserted(_data.value!!.size-1)
    }


    class AddLinkViewHolder(val binding: AddLinkListItemBinding) : RecyclerView.ViewHolder(binding.root)
}