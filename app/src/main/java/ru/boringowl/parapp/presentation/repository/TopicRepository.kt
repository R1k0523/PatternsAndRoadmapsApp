package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.Topic
import java.util.*

interface TopicRepository {
    fun getAllTopics(): LiveData<List<Topic>>
    suspend fun <T : Topic> addTopic(topic: T)
    fun getTopic(topicId: UUID): LiveData<Topic?>
    suspend fun <T : Topic> deleteTopic(topic: T)
}