package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import java.util.*

interface PostsRepository {
    fun getAllPosts(topicId: UUID): LiveData<List<Post>>
    suspend fun <T : Note> addNote(note: T)
    suspend fun <T : Roadmap> addRoadmap(roadmap: T)
    fun getNote(postId: UUID): LiveData<Note?>
    fun getRoadmap(postId: UUID): LiveData<Roadmap?>
    suspend fun deletePost(post: Post)

}