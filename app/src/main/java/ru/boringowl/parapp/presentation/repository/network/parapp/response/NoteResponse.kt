package ru.boringowl.parapp.presentation.repository.network.parapp.response

import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class NoteResponse(
    var postId: UUID? = null,
    val title: String,
    val image: String? = null,
    val publicationDateTime: String,
    val postCategories: List<String>,
    val postDescription: String,
    val topic: Topic,
    var creator: User? = null,
    var isNote: Boolean = true,
    val sections: List<NoteSection>,
    val docs: List<String>? = null,
){
    companion object {

        fun fromNote(note: Note) : NoteResponse {
            return NoteResponse(
                note.postId,
                note.title,
                note.image,
                note.publicationDateTime,
                note.postCategories,
                note.postDescription,
                note.topic,
                note.creator,
                note.isNote,
                note.sections,
                note.docs
            )
        }

    }
    fun toNote(): Note {
        return Note(
            postId,
            title,
            image,
            publicationDateTime,
            postCategories,
            postDescription,
            topic,
            creator,
            isNote,
            sections,
            docs
        )
    }
}
