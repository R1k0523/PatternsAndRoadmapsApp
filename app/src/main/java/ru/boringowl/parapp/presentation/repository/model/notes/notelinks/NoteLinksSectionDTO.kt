package ru.boringowl.parapp.presentation.repository.model.notes.notelinks

import androidx.room.PrimaryKey

data class NoteLinksSectionDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val linkItems: List<String>,
)