package ru.boringowl.parapp.domain.model.posts.notes

import ru.boringowl.parapp.domain.model.posts.Post

open class Note(
    open override val id: Int?,
    open override val title: String,
    open override val publicationDateTime: String,
    open override val postCategories: List<String>,
    open override val postDescription: String,
    open val sections: List<NoteSection>,
) : Post(
    id,
    title,
    publicationDateTime,
    postCategories,
    postDescription,
) {}