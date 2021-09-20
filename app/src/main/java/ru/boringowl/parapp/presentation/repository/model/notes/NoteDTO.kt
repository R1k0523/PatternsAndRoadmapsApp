package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection

@Entity(tableName = "notes")
data class NoteDTO(
    @ColumnInfo(name="description")
    override val title: String,
    @ColumnInfo(name="publication_date_time")
    override val publicationDateTime: String,
    @ColumnInfo(name="note_categories")
    override val postCategories: List<String>,
    @ColumnInfo(name="note_description")
    override val postDescription: String,
    @ColumnInfo(name="sections")
    override val sections: List<NoteSection>,
    @PrimaryKey(autoGenerate = true)
    override var id: Int? = null,
) : Note(
    id,
    title,
    publicationDateTime,
    postCategories,
    postDescription,
    sections,
) {

    constructor(note: Note) : this (
        note.title,
        note.publicationDateTime,
        note.postCategories,
        note.postDescription,
        note.sections,
    )
}