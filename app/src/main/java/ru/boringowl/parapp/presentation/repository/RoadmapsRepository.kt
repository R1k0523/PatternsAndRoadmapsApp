package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.notes.noteroadmap.Roadmap

interface RoadmapsRepository {
    fun <T : Roadmap> getAllRoadmaps(): LiveData<List<T>>
    fun <T : Roadmap> addRoadmap(roadmap: T)
    fun <T : Roadmap> getRoadmap(roadmapId: Int): T
    fun <T : Roadmap> deleteRoadmap(roadmap: T)
}