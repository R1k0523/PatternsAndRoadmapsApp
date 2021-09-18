package ru.boringowl.parapp.domain.model.posts.notes

import ru.boringowl.parapp.domain.model.posts.Post

open class Note(
    override val id: Int? = null,
    override val title: String,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    open val sections: List<NoteSection>,
) : Post(
    id,
    title,
    publicationDateTime,
    postCategories,
    postDescription,
)