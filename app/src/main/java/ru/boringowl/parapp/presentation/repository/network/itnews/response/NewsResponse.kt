package ru.boringowl.parapp.presentation.repository.network.itnews.response

import ru.boringowl.parapp.domain.model.news.Post


class NewsResponse {
    var status: String = ""
    var totalResults: Int = 0
    var articles: List<Post> = arrayListOf()
}