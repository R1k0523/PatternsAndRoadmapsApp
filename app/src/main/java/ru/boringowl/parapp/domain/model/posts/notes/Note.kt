package ru.boringowl.parapp.domain.model.posts.notes

import ru.boringowl.parapp.domain.model.posts.Post

open class Note(
    override var id: Int? = null,
    override val title: String,
    override val image: String? = null,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    open val sections: List<NoteSection>,
    open val docs: List<String>? = null,
) : Post(
    id,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
)