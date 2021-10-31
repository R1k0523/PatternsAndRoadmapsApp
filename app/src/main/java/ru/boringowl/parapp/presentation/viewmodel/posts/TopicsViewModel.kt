package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.presentation.repository.Repository

class TopicsViewModel : ViewModel() {
    fun getTopicsList(): LiveData<List<Topic>> {
        return Repository.topicRepository.getAllTopics()
    }

    fun deleteTopicInfo(topic: Topic) {
        viewModelScope.launch {
            Repository.topicRepository.deleteTopic(topic)
        }
    }

    fun addTopicInfo(topic: Topic) {
        viewModelScope.launch {
            Repository.topicRepository.addTopic(topic)
        }
    }
}