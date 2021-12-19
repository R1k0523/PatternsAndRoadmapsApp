package ru.boringowl.parapp.presentation.repository.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

@Entity(tableName = "topics")
data class TopicDTO(
    @ColumnInfo(name="topic_id")
    override var topicId: UUID? = null,
    @ColumnInfo(name="title")
    override var title: String?,
    @ColumnInfo(name="creator")
    override var creator: User?,
    @ColumnInfo(name="moderators")
    override var moderators: List<User>?
) : Topic(
    topicId,
    title,
    creator,
    moderators
) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int? = null
    constructor(topic: Topic) : this(
        topic.topicId,
        topic.title,
        topic.creator,
        topic.moderators
    )
}