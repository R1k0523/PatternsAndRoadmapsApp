package ru.boringowl.parapp.domain.model.posts.roadmaps

data class RoadmapNode(
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNode>,
    val isMainWay: Boolean = false
) {}