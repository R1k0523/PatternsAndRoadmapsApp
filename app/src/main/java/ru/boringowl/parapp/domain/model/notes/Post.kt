package ru.boringowl.parapp.domain.model.notes

import java.time.LocalDateTime

open class Post(
    open val id: Int,
    open val title: String,
    open val publicationDateTime: String,
    open val noteCategories: List<String>,
    open val noteDescription: String,
) {}