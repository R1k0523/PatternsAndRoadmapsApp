package ru.boringowl.parapp.domain.model.posts.roadmaps

import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class Roadmap(
    override var postId: UUID? = null,
    override val title: String,
    override val image: String? = null,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    override var topic: Topic,
    override var creator: User? = null,
    override var isNote: Boolean = false,
    open val root: RoadmapNode,
) : Post(
    postId,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
    topic,
    creator,
    isNote
)

