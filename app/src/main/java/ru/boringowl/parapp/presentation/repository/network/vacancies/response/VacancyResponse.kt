package ru.boringowl.parapp.presentation.repository.network.vacancies.response

import com.google.gson.annotations.SerializedName

class VacancyResponse(
                      @SerializedName("items")
                      val items: List<Vacancy>?) {
}
