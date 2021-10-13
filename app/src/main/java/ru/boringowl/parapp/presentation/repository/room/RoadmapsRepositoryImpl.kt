package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.room.dao.RoadmapsDAO
import ru.boringowl.parapp.presentation.repository.RoadmapsRepository
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO

class RoadmapsRepositoryImpl : RoadmapsRepository {
    private var roadmapsDAO: RoadmapsDAO
    private var allRoadmaps: LiveData<List<RoadmapDTO>>

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        roadmapsDAO = db.roadmapsDAO()
        allRoadmaps =  roadmapsDAO.getAllRoadmaps()
    }

    override fun <T : Roadmap> getAllRoadmaps(): LiveData<List<T>> {
        return allRoadmaps as LiveData<List<T>>
    }

    override suspend fun <T : Roadmap> addRoadmap(roadmap: T) {
        roadmapsDAO.addRoadmap(RoadmapDTO(roadmap))
    }

    override fun <T : Roadmap> getRoadmap(roadmapId: Int): T {
        return roadmapsDAO.getRoadmap(roadmapId) as T
    }

    override suspend fun <T : Roadmap> deleteRoadmap(roadmap: T) {
        roadmapsDAO.deleteRoadmap(roadmap as RoadmapDTO)
    }

}