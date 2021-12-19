package ru.boringowl.parapp.domain.model.posts.notes

import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class Note(
    override var postId: UUID? = null,
    override val title: String,
    override val image: String? = null,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    override var topic: Topic,
    override var creator: User? = null,
    override var isNote: Boolean = true,
    open val sections: List<NoteSection>,
    open val docs: List<String>? = null,
) : Post(
    postId,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
    topic,
    creator,
    isNote
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
