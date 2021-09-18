package ru.boringowl.parapp.domain.model.notes.noteroadmap

import ru.boringowl.parapp.domain.model.notes.Post

open class Roadmap(
    open override val id: Int,
    open override val title: String,
    open override val publicationDateTime: String,
    open override val noteCategories: List<String>,
    open override val noteDescription: String,
    open val root: RoadmapNode,
) : Post(
    id,
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
) {}