package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.Topic

@Entity(tableName = "topics")
data class TopicDTO(
    @PrimaryKey(autoGenerate = true)
    override val id: Int?,
    @ColumnInfo(name="title")
    override val title: String,
    @ColumnInfo(name="creator")
    override val creator: String,
    @ColumnInfo(name="moderators")
    override val moderators: List<String>
) : Topic(
    id,
    title,
    creator,
    moderators
) {
    constructor(topic: Topic) : this(
        topic.id,
        topic.title,
        topic.creator,
        topic.moderators
    )
}