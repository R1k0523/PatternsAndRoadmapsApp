package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

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
    @ColumnInfo(name="topic")
    override var topic: Topic,
    @ColumnInfo(name="creator")
    override var creator: User? = null,
    @ColumnInfo(name="isNote")
    override var isNote: Boolean = false,
    @ColumnInfo(name="post_id")
    override var postId: UUID? = null
) : Roadmap (
    postId,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
    topic,
    creator,
    isNote,
    root,
) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int? = null
    constructor(roadmap: Roadmap): this (
        roadmap.title,
        roadmap.image,
        roadmap.publicationDateTime,
        roadmap.postCategories,
        roadmap.postDescription,
        roadmap.root,
        roadmap.topic,
        roadmap.creator,
        roadmap.isNote,
        roadmap.postId,
    )
}