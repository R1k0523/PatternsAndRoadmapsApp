package ru.boringowl.parapp.data.model.notes.notelinks

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.time.LocalDateTime

data class NoteLinksDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val publicationDateTime: LocalDateTime,
    @ColumnInfo
    val noteCategoriesDTO: String, // TODO serialize categories
    @ColumnInfo
    val noteDescription: String,
    @ColumnInfo
    val sections: List<NoteLinksSectionDTO>,
)