package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.Topic

interface TopicRepository {
    fun <T : Topic> getAllTopics(): LiveData<List<T>>
    suspend fun <T : Topic> addTopic(topic: T)
    fun <T : Topic> getTopic(topicId: Int): T
    suspend fun <T : Topic> deleteTopic(topic: T)
}