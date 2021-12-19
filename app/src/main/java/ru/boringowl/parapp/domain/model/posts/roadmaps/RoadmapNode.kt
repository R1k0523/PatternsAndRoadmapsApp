package ru.boringowl.parapp.domain.model.posts.roadmaps

import java.util.*

data class RoadmapNode(
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNode>,
    val isMainWay: Boolean = false,
    val id: UUID? = null,
)