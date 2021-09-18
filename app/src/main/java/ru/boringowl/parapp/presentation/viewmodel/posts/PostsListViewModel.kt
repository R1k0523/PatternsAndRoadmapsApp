package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository

class PostsListViewModel : ViewModel() {
    private val _posts = MediatorLiveData<List<Post>>()
    val posts: MutableLiveData<List<Post>>
        get() = _posts
    private val _isFABOpen = MutableLiveData<Boolean>()
    val isFABOpen: LiveData<Boolean>
        get() = _isFABOpen

    init {
        _isFABOpen.value = false
    }

    fun setFABOpen() {
        _isFABOpen.value = true
    }
    fun setFABClosed() {
        _isFABOpen.value = false
    }

    fun getPostsList(): LiveData<List<Post>> {
        val notes: LiveData<List<Note>> = Repository.notesRep!!.getAllNotes()
        val roadmaps: LiveData<List<Roadmap>> = Repository.roadmapsRep!!.getAllRoadmaps()
        _posts.removeSource(notes)
        _posts.addSource(notes) {
            posts.value = (posts.value?.filterNot { post -> post is Note } ?: listOf()) + it
        }
        _posts.removeSource(roadmaps)
        _posts.addSource(roadmaps) {
            posts.value = (posts.value?.filterNot { post -> post is Roadmap } ?: listOf()) + it
        }
        return posts
    }

    fun deletePost(post: Post) {
        if (post is Roadmap) {
            Repository.roadmapsRep!!.deleteRoadmap(post)
        } else if (post is Note){
            Repository.notesRep!!.deleteNote(post)
        }
    }

    fun addNote(note: Note) {
        Repository.notesRep!!.addNote(note)
    }
    fun addRoadmap(roadmap: Roadmap) {
        Repository.roadmapsRep!!.addRoadmap(roadmap)
    }

}