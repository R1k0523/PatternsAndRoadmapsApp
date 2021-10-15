package ru.boringowl.parapp.presentation.view.posts.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.databinding.VacancyListItemBinding
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.Vacancy

class VacancyAdapter(val data : List<Vacancy>, val context: Context) :
    RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding: VacancyListItemBinding =
            VacancyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = data[position]
        holder.binding.vacancy = vacancy
        holder.binding.root.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vacancy.alternateUrl))
            context.startActivity(browserIntent)
        }
    }

    class VacancyViewHolder(val binding: VacancyListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

@BindingAdapter("app:vacancies", "app:context")
fun setRecyclerVacancies(recyclerView: RecyclerView, vacancies: List<Vacancy>?, context: Context) {
    if (vacancies != null)
        recyclerView.apply {
            Log.d("VACS_BIND", vacancies.size.toString()+"_____________")
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            adapter =  VacancyAdapter(vacancies, context)
        }
}