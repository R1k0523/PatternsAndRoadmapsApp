package ru.boringowl.parapp.presentation.repository.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.PostsRepository
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import ru.boringowl.parapp.presentation.repository.network.parapp.PostsService
import ru.boringowl.parapp.presentation.repository.room.dao.NotesDAO
import ru.boringowl.parapp.presentation.repository.room.dao.RoadmapsDAO
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.util.*

class PostsRepositoryImpl : PostsRepository {
    private var notesDAO: NotesDAO
    private var allPosts: LiveData<List<Post>>
    private var roadmapsDAO: RoadmapsDAO
    private val postsService: PostsService by inject(PostsService::class.java)
    private val prefs by inject(PrefsUtils::class.java)
    private var topicId: UUID? = null
    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        notesDAO = db.notesDAO()
        roadmapsDAO = db.roadmapsDAO()
        allPosts = MutableLiveData()
    }


    override fun getAllPosts(topicId: UUID): LiveData<List<Post>> {
        this.topicId = topicId
        allPosts = liveData {
            val disposable = emitSource(
                mergePosts(topicId)
            )
            try {
                val posts = postsService.getPostsByTopic(topicId)
                disposable.dispose()
                emitSource(
                    MutableLiveData(posts)
                )
            } catch (exception: Exception) {
                Log.d("Post", exception.localizedMessage)
                emitSource(
                    mergePosts(topicId)
                )
            }
        }
        return allPosts
    }
    override suspend fun <T : Roadmap> addRoadmap(roadmap: T) {
        try {
            val savedRoadmap = postsService.createRoadmap(prefs.getToken()!!, roadmap)
            roadmapsDAO.addRoadmap(RoadmapDTO(savedRoadmap))
        } catch (e: Exception) {}
        topicId?.let { getAllPosts(it) }
    }

    override suspend fun <T : Note> addNote(note: T) {
        try {
            val savedNote = postsService.createNote(prefs.getToken()!!, note)
            notesDAO.addNote(NoteDTO(savedNote))
        } catch (e: Exception) {}
        topicId?.let { getAllPosts(it) }
    }

    override fun getRoadmap(postId: UUID): LiveData<Roadmap?> {
        val post: LiveData<Roadmap?> = liveData {
            val disposable = emitSource(
                roadmapsDAO.getRoadmap(postId) as LiveData<Roadmap?>
            )
            try {
                val post = postsService.getRoadmap(postId)
                disposable.dispose()
                roadmapsDAO.deleteRoadmap(post.postId!!)
                roadmapsDAO.addRoadmap(RoadmapDTO(post))
                emitSource(
                    roadmapsDAO.getRoadmap(postId) as LiveData<Roadmap?>
                )

            } catch (exception: Exception) {
                emitSource(
                    if (notesDAO.isRowIsExist(postId))
                        notesDAO.getNote(postId.toString()) as LiveData<Roadmap?>
                    else
                        roadmapsDAO.getRoadmap(postId) as LiveData<Roadmap?>
                )
            }
        }
        return post
    }
    override fun getNote(postId: UUID): LiveData<Note?> {
        val post: LiveData<Note?> = liveData {
            val disposable = emitSource(
                notesDAO.getNote(postId.toString()) as LiveData<Note?>
            )
            try {
                val post = postsService.getNote(postId)
                disposable.dispose()
                notesDAO.deleteNote(post.postId!!)
                notesDAO.addNote(NoteDTO(post))
                emitSource(
                    notesDAO.getNote(postId.toString()) as LiveData<Note?>
                )
            } catch (exception: Exception) {
                notesDAO.getNote(postId.toString()) as LiveData<Note?>
            }
        }
        return post
    }
    override suspend fun deletePost(post: Post) {
        postsService.deletePost(prefs.getToken()!!, post.postId!!)
        if (notesDAO.isRowIsExist(post.postId!!)) {
            notesDAO.deleteNote(post.postId!!)
        }
        if (roadmapsDAO.isRowIsExist(post.postId!!)) {
            roadmapsDAO.deleteRoadmap(post.postId!!)
        }
        topicId?.let { getAllPosts(it) }
    }


    fun mergePosts(topicId: UUID) : MediatorLiveData<List<Post>> {
        val posts = MediatorLiveData<List<Post>>()
        val notes: LiveData<List<Note>> = Repository.notesRep.getAllNotes(topicId)
        val roadmaps: LiveData<List<Roadmap>> = Repository.roadmapsRep.getAllRoadmaps(topicId)
        posts.removeSource(notes)
        posts.addSource(notes) {
            posts.value = (posts.value?.filterNot { post -> post is Note } ?: listOf()) + it
        }
        posts.removeSource(roadmaps)
        posts.addSource(roadmaps) {
            posts.value = (posts.value?.filterNot { post -> post is Roadmap } ?: listOf()) + it
        }
        return posts
    }

}