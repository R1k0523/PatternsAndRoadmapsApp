package ru.boringowl.parapp.presentation.repository.network.parapp

import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.parapp.response.GenericResponse
import java.util.*

class TopicService(private val api: ParappDataAPI) : BaseService() {
    suspend fun createTopic(token: String, topic: Topic) : Topic {
        return when(val result = createCall { api.createTopic(token, topic) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
    suspend fun updateTopic(token: String, topic: Topic) : Topic {
        return when(val result = createCall { api.updateTopic(token, topic) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
    suspend fun deleteTopic(token: String, id: UUID) : GenericResponse {
        return when(val result = createCall { api.deleteTopic(token, id)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }

    }
    suspend fun getTopic(id: UUID) : Topic {
        return when(val result = createCall { api.getTopic(id) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
    suspend fun getAllTopics() : List<Topic> {
        return when(val result = createCall { api.getAllTopics() }){
            is MyResult.Success -> result.data.items
            is MyResult.Error -> throw result.error
        }
    }
}