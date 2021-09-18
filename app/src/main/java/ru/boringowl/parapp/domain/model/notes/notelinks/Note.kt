package ru.boringowl.parapp.domain.model.notes.notelinks

import ru.boringowl.parapp.domain.model.notes.Post

open class Note(
    open override val id: Int,
    open override val title: String,
    open override val publicationDateTime: String,
    open override val noteCategories: List<String>,
    open override val noteDescription: String,
    open val sections: List<NoteSection>,
) : Post(
    id,
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
) {}