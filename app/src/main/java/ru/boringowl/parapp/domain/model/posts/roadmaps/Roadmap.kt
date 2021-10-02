package ru.boringowl.parapp.domain.model.posts.roadmaps

import ru.boringowl.parapp.domain.model.posts.Post

open class Roadmap(
    override var id: Int? = null,
    override val title: String,
    override val image: String? = null,
    override val publicationDateTime: String,
    override val postCategories: List<String>,
    override val postDescription: String,
    open val root: RoadmapNode,
) : Post(
    id,
    title,
    image,
    publicationDateTime,
    postCategories,
    postDescription,
)

