package ru.boringowl.parapp.presentation.repository.network.vacancies

import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.itnews.response.NewsResponse
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.VacancyResponse

class VacancyService(private val api: HeadHunterAPI) : BaseService() {
    suspend fun fetchVacancies(text: String, page: Int): MyResult<VacancyResponse> {
        return createCall { api.vacancies(text = text, page=page) }
    }
}