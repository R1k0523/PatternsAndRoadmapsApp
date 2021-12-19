package ru.boringowl.parapp.presentation.repository.network.parapp

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.http.*
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.parapp.response.GenericResponse
import java.lang.reflect.Type
import com.fasterxml.jackson.databind.ObjectMapper
import ru.boringowl.parapp.presentation.repository.network.parapp.response.NoteResponse
import ru.boringowl.parapp.presentation.repository.network.parapp.response.RoadmapResponse
import java.util.*

class PostsService(private val api: ParappDataAPI) : BaseService() {
    suspend fun createNote(token: String, note: Note) : Note {

        return when(val result = createCall { api.createNote(token, NoteResponse.fromNote(note))}){
            is MyResult.Success -> result.data.toNote()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun updateNote(token: String, note: Note) : Note {

        return when(val result = createCall { api.updateNote(token, NoteResponse.fromNote(note))}){
            is MyResult.Success -> result.data.toNote()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun createRoadmap(token: String, roadmap: Roadmap) : Roadmap {

        return when(val result = createCall { api.createRoadmap(token, RoadmapResponse.fromRoadmap(roadmap))}){
            is MyResult.Success -> result.data.toRoadmap()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun updateRoadmap(token: String, roadmap: Roadmap) : Roadmap {

        return when(val result = createCall { api.updateRoadmap(token, RoadmapResponse.fromRoadmap(roadmap))}){
            is MyResult.Success -> result.data.toRoadmap()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun deletePost(token: String, id: UUID) : GenericResponse {

        return when(val result = createCall { api.deletePost(token, id)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun getRoadmap(id: UUID) : Roadmap {
        return when(val result = createCall { api.getRoadmap(id)}){
            is MyResult.Success -> result.data.toRoadmap()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun getNote(id: UUID) : Note {
        return when(val result = createCall { api.getNote(id)}){
            is MyResult.Success -> result.data.toNote()
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun getPostsByTopic(id: UUID) : List<Post> {
        return when(val result = createCall { api.getPostsByTopic(id)}) {
            is MyResult.Success -> result.data.items
            is MyResult.Error -> throw result.error
        }
    }

}