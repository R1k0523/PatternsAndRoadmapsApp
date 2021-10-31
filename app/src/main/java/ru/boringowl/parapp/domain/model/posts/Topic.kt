package ru.boringowl.parapp.domain.model.posts

open class Topic(
    open val id: Int?,
    open val title: String,
    open val creator: String,
    open val moderators: List<String>
)