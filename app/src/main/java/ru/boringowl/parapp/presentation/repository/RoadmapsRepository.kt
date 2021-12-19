package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import java.util.*

interface RoadmapsRepository {
    fun <T : Roadmap> getAllRoadmaps(): LiveData<List<T>>
    fun <T : Roadmap> getAllRoadmaps(topicId: UUID): LiveData<List<T>>
    suspend fun <T : Roadmap> addRoadmap(roadmap: T)
    fun <T : Roadmap> getRoadmap(roadmapId: UUID): T
    suspend fun <T : Roadmap> deleteRoadmap(roadmap: T)
}