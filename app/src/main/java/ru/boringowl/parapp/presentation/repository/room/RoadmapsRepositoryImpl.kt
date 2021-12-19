package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.room.dao.RoadmapsDAO
import ru.boringowl.parapp.presentation.repository.RoadmapsRepository
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.util.*

class RoadmapsRepositoryImpl : RoadmapsRepository {
    private var roadmapsDAO: RoadmapsDAO
    private var allRoadmaps: LiveData<List<RoadmapDTO>>
    private val prefs by inject(PrefsUtils::class.java)

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        roadmapsDAO = db.roadmapsDAO()
        allRoadmaps =  roadmapsDAO.getAllRoadmaps()
    }

    override fun <T : Roadmap> getAllRoadmaps(): LiveData<List<T>> {
        return allRoadmaps as LiveData<List<T>>
    }

    override fun <T : Roadmap> getAllRoadmaps(topicId: UUID): LiveData<List<T>> {
        return roadmapsDAO.getAllRoadmaps(topicId) as LiveData<List<T>>
    }

    override suspend fun <T : Roadmap> addRoadmap(roadmap: T) {
        roadmapsDAO.addRoadmap(RoadmapDTO(roadmap))
    }

    override fun <T : Roadmap> getRoadmap(roadmapId: UUID): T {
        return roadmapsDAO.getRoadmap(roadmapId) as T
    }

    override suspend fun <T : Roadmap> deleteRoadmap(roadmap: T) {
        roadmapsDAO.deleteRoadmap(roadmap as RoadmapDTO)
    }

}