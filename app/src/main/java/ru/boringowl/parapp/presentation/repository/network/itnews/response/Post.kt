package ru.boringowl.parapp.presentation.repository.network.itnews.response



data class Post(
    val source : Source,
    val author : String,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String?,
    val publishedAt : String,
) {
    data class Source(
        val id : String,
        val name : String,
    )
}