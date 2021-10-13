package ru.boringowl.parapp.presentation.repository.network.vacancies

import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.VacancyResponse

class VacancyRepository(private val service: VacancyService) {
    suspend fun getVacancies(text: String, page: Int = 0) : VacancyResponse {
        return when(val result = service.fetchVacancies(text, page)){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
}