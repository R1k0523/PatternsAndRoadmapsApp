package ru.boringowl.parapp.data.model.notes.noteroadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "roadmaps")
data class NoteRoadmapDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val publicationDateTime: LocalDateTime,
    @ColumnInfo
    val noteCategoriesDTO: String,
    @ColumnInfo
    val noteDescription: String,
    @ColumnInfo
    val root: RoadmapNodeDTO,
)