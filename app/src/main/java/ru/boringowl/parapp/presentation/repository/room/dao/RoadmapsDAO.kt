package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO
import java.util.*

@Dao
interface RoadmapsDAO {

    @Insert
    fun addRoadmap(roadmap: RoadmapDTO?)

    @Query("SELECT EXISTS(SELECT * FROM roadmaps WHERE post_id = :id)")
    fun isRowIsExist(id : UUID) : Boolean

    @Delete
    fun deleteRoadmap(pattern: RoadmapDTO?)

    @Query("DELETE FROM roadmaps WHERE post_id = :roadmapId")
    fun deleteRoadmap(roadmapId: UUID)

    @Query("SELECT * FROM roadmaps")
    fun getAllRoadmaps(): LiveData<List<RoadmapDTO>>

    @Query("SELECT * FROM roadmaps WHERE post_id = :roadmapId LIMIT 1")
    fun getRoadmap(roadmapId: UUID): LiveData<RoadmapDTO?>


    @Query("SELECT * FROM roadmaps WHERE topic = :topicId")
    fun getAllRoadmaps(topicId: UUID): LiveData<List<RoadmapDTO>>
}