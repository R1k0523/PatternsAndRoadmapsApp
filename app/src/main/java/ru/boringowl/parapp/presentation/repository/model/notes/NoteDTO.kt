package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.notes.Post
import ru.boringowl.parapp.domain.model.notes.notelinks.Note
import ru.boringowl.parapp.domain.model.notes.notelinks.NoteSection

@Entity(tableName = "notes")
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name="description")
    override val title: String,
    @ColumnInfo(name="publication_date_time")
    override val publicationDateTime: String,
    @ColumnInfo(name="note_categories")
    override val noteCategories: List<String>,
    @ColumnInfo(name="note_description")
    override val noteDescription: String,
    @ColumnInfo(name="sections")
    override val sections: List<NoteSection>,
) : Note(
    id,
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
    sections,
) {

    constructor(note: Note) : this (
        note.id,
        note.title,
        note.publicationDateTime,
        note.noteCategories,
        note.noteDescription,
        note.sections,
    )
}