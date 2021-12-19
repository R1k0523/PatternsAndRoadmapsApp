package ru.boringowl.parapp.domain.model.posts.notes

import java.util.*

data class NoteSection(
    var description: String,
    var linkItems: List<String>,
    val sectionId: UUID? = null,
)