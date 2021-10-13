package ru.boringowl.parapp.presentation.repository.network.vacancies.response

import com.google.gson.annotations.SerializedName

class VacancyResponse(@SerializedName("per_page")
                      val perPage: Int = 0,
                      @SerializedName("page")
                      val page: Int = 0,
                      @SerializedName("items")
                      val items: List<Vacancy>?) {
}