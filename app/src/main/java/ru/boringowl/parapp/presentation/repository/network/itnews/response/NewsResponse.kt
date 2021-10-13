package ru.boringowl.parapp.presentation.repository.network.itnews.response


class NewsResponse {
    var status: String = ""
    var totalResults: Int = 0
    var articles: List<Post> = arrayListOf()
}