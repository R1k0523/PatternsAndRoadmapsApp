package ru.boringowl.parapp.domain.model.notes.noteroadmap

class RoadmapNode(
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNode>,
) {}