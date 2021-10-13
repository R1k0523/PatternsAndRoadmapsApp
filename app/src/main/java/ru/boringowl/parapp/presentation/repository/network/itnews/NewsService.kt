package ru.boringowl.parapp.presentation.repository.network.itnews

import ru.boringowl.parapp.domain.model.news.MyResult
import ru.boringowl.parapp.presentation.repository.network.itnews.response.NewsResponse

class NewsService(private val api: NewsAPI) : BaseService() {
    suspend fun fetchNews(page: Int): MyResult<NewsResponse> {
        return createCall { api.news(page=page) }
    }
}