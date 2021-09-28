package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode

@Entity(tableName = "roadmaps")
data class RoadmapDTO(
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
    @ColumnInfo(name="root")
    override val root: RoadmapNode,
    @PrimaryKey(autoGenerate = true)
    override var id: Int? = null,
) : Roadmap (
    id,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
    root,
) {
    constructor(roadmap: Roadmap): this (
        roadmap.title,
        roadmap.image,
        roadmap.publicationDateTime,
        roadmap.postCategories,
        roadmap.postDescription,
        roadmap.root,
        roadmap.id,
    )
}