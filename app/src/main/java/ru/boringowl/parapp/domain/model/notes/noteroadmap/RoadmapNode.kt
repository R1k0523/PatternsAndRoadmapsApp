package ru.boringowl.parapp.domain.model.notes.noteroadmap

import ru.boringowl.parapp.presentation.repository.model.notes.noteroadmap.RoadmapNodeDTO

class RoadmapNode(
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNodeDTO>,
) {}