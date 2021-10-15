package ru.boringowl.parapp.presentation.repository.network.vacancies

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.VacancyResponse


interface HeadHunterAPI {
    @GET("vacancies")
    suspend fun vacancies(
        @Query("text") text: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("area") areaId: Int = 1, //Москва
        ): Response<VacancyResponse>
}
