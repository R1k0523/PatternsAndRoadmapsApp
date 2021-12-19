package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository
import java.util.*

class AddRoadmapViewModel : ViewModel() {
    private val _image = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = _image

    fun setImage(image: String?) {
        _image.value = image
    }

    fun save(roadmap: Roadmap) : Job {
        return viewModelScope.launch {
            roadmap.creator = Repository.currentUser.value
            Repository.postsRepository.addRoadmap(roadmap)
        }
    }
    fun getTopic(topicId: UUID) = Repository.topicRepository.getTopic(topicId)
}