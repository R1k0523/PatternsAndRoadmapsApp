package ru.boringowl.parapp.domain.model.posts.notes

import ru.boringowl.parapp.domain.model.posts.Post

open class Note(
    override var id: Int? = null,
    override val title: String,
    override val image: String? = null,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    override val topic: Int,
    open val sections: List<NoteSection>,
    open val docs: List<String>? = null,
) : Post(
    id,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
    topic,
) {
    fun getKeyWords(): String {
        val regex = Regex("[A-Z][A-z]+")
        val query = arrayListOf<String>()
        var text = categories() + " "
        sections?.forEach { it ->
            text += it.description
        }
        regex.findAll(text).iterator().forEach {
            if (it.value !in query)
                query.add(it.value)
        }
        return query.joinToString(" OR ")
    }
}
