package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.notes.noteroadmap.Roadmap
import ru.boringowl.parapp.domain.model.notes.noteroadmap.RoadmapNode

@Entity(tableName = "roadmaps")
data class RoadmapDTO(
    @ColumnInfo(name="title")
    override val title: String,
    @ColumnInfo(name="publication_date_time")
    override val publicationDateTime: String,
    @ColumnInfo(name="note_categories")
    override val noteCategories: List<String>,
    @ColumnInfo(name="note_description")
    override val noteDescription: String,
    @ColumnInfo(name="root")
    override val root: RoadmapNode,
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
) : Roadmap (
    id,
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
    root,
) {
    constructor(roadmap: Roadmap): this (
        roadmap.title,
        roadmap.publicationDateTime,
        roadmap.noteCategories,
        roadmap.noteDescription,
        roadmap.root,
        roadmap.id,
    )
}