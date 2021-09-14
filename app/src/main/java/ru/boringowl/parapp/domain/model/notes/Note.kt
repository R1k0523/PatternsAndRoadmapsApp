package ru.boringowl.parapp.domain.model.notes

import java.time.LocalDateTime

abstract class Note(
    open val title: String,
    open val publicationDateTime: LocalDateTime,
    open val noteCategories: List<NoteCategory>,
    open val noteDescription: String,
) {}