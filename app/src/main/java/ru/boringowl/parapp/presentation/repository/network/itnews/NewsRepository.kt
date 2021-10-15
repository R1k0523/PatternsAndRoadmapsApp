package ru.boringowl.parapp.presentation.repository.network.itnews

import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.itnews.response.NewsResponse

class NewsRepository(private val service: NewsService) {
    suspend fun getPopularNews(page: Int) : NewsResponse {
        return when(val result = service.fetchNews(page)){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
}
