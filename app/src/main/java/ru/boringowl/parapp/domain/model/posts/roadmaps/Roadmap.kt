package ru.boringowl.parapp.domain.model.posts.roadmaps

import ru.boringowl.parapp.domain.model.posts.Post

open class Roadmap(
    open override val id: Int,
    open override val title: String,
    open override val publicationDateTime: String,
    open override val postCategories: List<String>,
    open override val postDescription: String,
    open val root: RoadmapNode,
) : Post(
    id,
    title,
    publicationDateTime,
    postCategories,
    postDescription,
) {

}