package ru.boringowl.parapp.presentation.repository.room

import androidx.lifecycle.LiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.repository.TopicRepository
import ru.boringowl.parapp.presentation.repository.model.notes.TopicDTO
import ru.boringowl.parapp.presentation.repository.room.dao.TopicDAO

class TopicRepositoryImpl : TopicRepository {
    private var topicDAO: TopicDAO
    private var allTopics: LiveData<List<TopicDTO>>

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        topicDAO = db.topicDAO()
        allTopics =  topicDAO.getAllTopics()
    }

    override fun <T : Topic> getAllTopics(): LiveData<List<T>> {
        return allTopics as LiveData<List<T>>
    }

    override suspend fun <T : Topic> addTopic(topic: T) {
        topicDAO.addTopic(TopicDTO(topic))
    }

    override fun <T : Topic> getTopic(topicId: Int): T {
        return topicDAO.getTopic(topicId) as T
    }

    override suspend fun <T : Topic> deleteTopic(topic: T) {
        topicDAO.deleteTopic(topic as TopicDTO)
    }

}