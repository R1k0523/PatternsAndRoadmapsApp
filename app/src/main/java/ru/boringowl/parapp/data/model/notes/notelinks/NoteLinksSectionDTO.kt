package ru.boringowl.parapp.data.model.notes.notelinks

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NoteLinksSectionDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val linkItems: List<String>,
)