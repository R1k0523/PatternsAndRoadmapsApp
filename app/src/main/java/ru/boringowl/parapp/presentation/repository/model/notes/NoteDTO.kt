package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

@Entity(tableName = "notes")
data class NoteDTO(
    @ColumnInfo(name="title")
    override val title: String,
    @ColumnInfo(name="image")
    override val image: String?,
    @ColumnInfo(name="publication_date_time")
    override val publicationDateTime: String,
    @ColumnInfo(name="note_categories")
    override val postCategories: List<String>,
    @ColumnInfo(name="note_description")
    override val postDescription: String,
    @ColumnInfo(name="sections")
    override val sections: List<NoteSection>,
    @ColumnInfo(name="docs")
    override val docs: List<String>?,
    @ColumnInfo(name="topic")
    override var topic: Topic,
    @ColumnInfo(name="creator")
    override var creator: User? = null,
    @ColumnInfo(name="is_note")
    override var isNote: Boolean = true,
    @ColumnInfo(name="post_id")
    override var postId: UUID? = null
) : Note(
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
    docs,
) {

    @PrimaryKey(autoGenerate = true)
    var localId: Int? = null
    constructor(note: Note) : this (
        note.title,
        note.image,
        note.publicationDateTime,
        note.postCategories,
        note.postDescription,
        note.sections,
        note.docs,
        note.topic,
        note.creator,
        note.isNote,
        note.postId,
    )
}