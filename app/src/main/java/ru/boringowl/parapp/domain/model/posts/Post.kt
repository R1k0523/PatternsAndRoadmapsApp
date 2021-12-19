package ru.boringowl.parapp.domain.model.posts

import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class Post(
    open val postId: UUID?,
    open val title: String,
    open val image: String? = null,
    open val publicationDateTime: String,
    open val postCategories: List<String>,
    open val postDescription: String,
    open val topic: Topic,
    open var creator: User? = null,
    open var isNote: Boolean = true,
) {
    fun categories():String {
    return postCategories.joinToString(
        prefix = "",
        separator = " ",
        postfix = ",",
        transform = { it.uppercase() }
    )
}}