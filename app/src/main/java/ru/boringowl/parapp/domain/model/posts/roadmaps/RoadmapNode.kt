package ru.boringowl.parapp.domain.model.posts.roadmaps

class RoadmapNode(
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNode>,
) {}