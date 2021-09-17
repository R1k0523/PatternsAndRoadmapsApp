package ru.boringowl.parapp.presentation.repository.model.notes.noteroadmap

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "roadmaps")
data class NoteRoadmapDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val publicationDateTime: LocalDateTime,
    val noteCategoriesDTO: String,
    val noteDescription: String,
    val root: RoadmapNodeDTO,
)