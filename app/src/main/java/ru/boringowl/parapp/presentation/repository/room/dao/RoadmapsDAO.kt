package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO

@Dao
interface RoadmapsDAO {

    @Insert
    fun addRoadmap(roadmap: RoadmapDTO?)

    @Delete
    fun deleteRoadmap(pattern: RoadmapDTO?)

    @Query("SELECT * FROM roadmaps")
    fun getAllRoadmaps(): LiveData<List<RoadmapDTO>>

    @Query("SELECT * FROM roadmaps WHERE id = :roadmapId LIMIT 1")
    fun getRoadmap(roadmapId: Int): RoadmapDTO


    @Query("SELECT * FROM roadmaps WHERE topic = :topicId")
    fun getAllRoadmaps(topicId: Int): LiveData<List<RoadmapDTO>>
}