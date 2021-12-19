package ru.boringowl.parapp.presentation.repository.network.parapp.response

import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class RoadmapResponse(
    var postId: UUID? = null,
    val title: String,
    val image: String? = null,
    val publicationDateTime: String,
    val postCategories: List<String>,
    val postDescription: String,
    val topic: Topic,
    var creator: User? = null,
    var isNote: Boolean = false,
    val root: RoadmapNode,
) {
    companion object {
        fun fromRoadmap(roadmap: Roadmap) : RoadmapResponse {
            return RoadmapResponse(
                roadmap.postId,
                roadmap.title,
                roadmap.image,
                roadmap.publicationDateTime,
                roadmap.postCategories,
                roadmap.postDescription,
                roadmap.topic,
                roadmap.creator,
                roadmap.isNote,
                roadmap.root,
            )
        }
    }
    fun toRoadmap(): Roadmap {
        return Roadmap(
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
        )
    }
}