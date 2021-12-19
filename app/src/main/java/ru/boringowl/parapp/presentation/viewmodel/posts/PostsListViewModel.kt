package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository
import java.util.*

class PostsListViewModel(val topicId: UUID) : ViewModel() {
    val topic: LiveData<Topic?> = Repository.topicRepository.getTopic(topicId)
    private var _posts: LiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>>
        get() = _posts
    private val _isFABOpen = MutableLiveData<Boolean>()
    val isFABOpen: LiveData<Boolean>
        get() = _isFABOpen

    init {
        _posts = Repository.postsRepository.getAllPosts(topicId)
        _isFABOpen.value = false
    }

    fun setFABOpen() {
        _isFABOpen.value = true
    }
    fun setFABClosed() {
        _isFABOpen.value = false
    }

    fun getPostsList(): LiveData<List<Post>> {
        return posts
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            Repository.postsRepository.deletePost(post)
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            note.creator = Repository.currentUser.value
            note.topic = Topic(topicId, null, null, null)
            Repository.postsRepository.addNote(note)
            _posts = Repository.postsRepository.getAllPosts(topicId)
        }
    }
    fun addRoadmap(roadmap: Roadmap) {
        viewModelScope.launch {
            roadmap.creator = Repository.currentUser.value
            roadmap.topic = Topic(topicId, null, null, null)
            Repository.postsRepository.addRoadmap(roadmap)
            _posts = Repository.postsRepository.getAllPosts(topicId)
        }
    }

}