package ru.boringowl.parapp.domain.model.notes.notelinks

import ru.boringowl.parapp.domain.model.notes.Note
import ru.boringowl.parapp.domain.model.notes.NoteCategory
import java.time.LocalDateTime

data class NoteLinks(
    override val title: String,
    override val publicationDateTime: LocalDateTime,
    override val noteCategories: List<NoteCategory>,
    override val noteDescription: String,
    val sections: List<NoteLinksSection>,
) : Note(
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
) {}