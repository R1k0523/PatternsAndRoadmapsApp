package ru.boringowl.parapp.domain.model.posts

open class Post(
    open val id: Int?,
    open val title: String,
    open val image: String? = null,
    open val publicationDateTime: String,
    open val postCategories: List<String>,
    open val postDescription: String,
    open val topic: Int,
) {
    fun categories():String {
    return postCategories.joinToString(
        prefix = "",
        separator = " ",
        postfix = ",",
        transform = { it.uppercase() }
    )
}}