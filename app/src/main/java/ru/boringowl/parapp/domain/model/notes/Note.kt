package ru.boringowl.parapp.domain.model.notes

import java.time.LocalDateTime

open class Note(
    open val title: String,
    open val publicationDateTime: LocalDateTime,
    open val noteCategories: List<NoteCategory>,
    open val noteDescription: String,
) {}