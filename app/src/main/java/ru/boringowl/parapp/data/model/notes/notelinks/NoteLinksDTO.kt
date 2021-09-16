package ru.boringowl.parapp.data.model.notes.notelinks

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.time.LocalDateTime

data class NoteLinksDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val publicationDateTime: LocalDateTime,
    val noteCategoriesDTO: String, // TODO serialize categories
    val noteDescription: String,
    val sections: List<NoteLinksSectionDTO>,
)