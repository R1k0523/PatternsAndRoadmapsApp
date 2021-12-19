package ru.boringowl.parapp.domain.model.posts

import ru.boringowl.parapp.domain.model.user.User
import java.util.*

open class Topic(
    open var topicId: UUID?,
) {
    open var title: String? = null
    open var creator: User? = null
    open var moderators: List<User>? = null

    constructor(topicId: UUID?,
                title: String?,
                creator: User?,
                moderators: List<User>?) : this(topicId) {
        this.title = title
        this.creator = creator
        this.moderators = moderators
        }
}