package ru.boringowl.parapp.presentation.repository.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.repository.TopicRepository
import ru.boringowl.parapp.presentation.repository.model.notes.TopicDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import ru.boringowl.parapp.presentation.repository.network.parapp.TopicService
import ru.boringowl.parapp.presentation.repository.room.dao.TopicDAO
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.util.*

class TopicRepositoryImpl : TopicRepository {
    private var topicDAO: TopicDAO
    private var allTopics: LiveData<List<Topic>>
    private val topicService: TopicService by inject(TopicService::class.java)
    private val prefs by inject(PrefsUtils::class.java)

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        topicDAO = db.topicDAO()
        allTopics =  liveData {
            val disposable = emitSource(
                topicDAO.getAllTopics() as LiveData<List<Topic>>
            )
            try {
                val topics = topicService.getAllTopics()
                disposable.dispose()
                topicDAO.deleteAllTopics()
                topics.forEach {
                    topicDAO.addTopic(TopicDTO(it))
                }
                emitSource(
                    topicDAO.getAllTopics() as LiveData<List<Topic>>
                )
            } catch (exception: Exception) {
                emitSource(
                    topicDAO.getAllTopics() as LiveData<List<Topic>>
                )
            }
        }
    }

    override fun getAllTopics(): LiveData<List<Topic>> {
        return allTopics
    }

    override suspend fun <T : Topic> addTopic(topic: T) {
        try {
            val savedNote = topicService.createTopic(prefs.getToken()!!, topic)
            topicDAO.addTopic(TopicDTO(savedNote))
        } catch (e: Exception) {}
        getAllTopics()
    }

    override fun getTopic(topicId: UUID): LiveData<Topic?> {
        val topic: LiveData<Topic?> = liveData {
            val disposable = emitSource(
                topicDAO.getTopic(topicId) as LiveData<Topic?>
            )
            try {
                val topic = topicService.getTopic(topicId)
                disposable.dispose()
                topicDAO.deleteTopic(topic.topicId!!)
                topicDAO.addTopic(TopicDTO(topic))
                emitSource(
                    topicDAO.getTopic(topicId) as LiveData<Topic?>
                )
            } catch (exception: Exception) {
                topicDAO.getTopic(topicId) as LiveData<Topic?>
            }
        }
        return topic
    }

    override suspend fun <T : Topic> deleteTopic(topic: T) {
        topicService.deleteTopic(prefs.getToken()!!, topic.topicId!!)
        topicDAO.deleteTopic(topic as TopicDTO)
    }

}