package ru.boringowl.parapp.presentation.repository.room.dao

import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import androidx.lifecycle.LiveData
import androidx.room.*
import ru.boringowl.parapp.presentation.repository.model.notes.TopicDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import java.util.*

@Dao
interface TopicDAO {
    @Insert
    fun addTopic(topic: TopicDTO?)

    @Query("SELECT * FROM topics WHERE creator = :email")
    fun getTopicByEmail(email: String?): LiveData<List<TopicDTO?>?>?

    @Query("SELECT * FROM topics")
    fun getAllTopics(): LiveData<List<TopicDTO>>

    @Query("DELETE FROM topics WHERE topic_id = :topicId")
    fun deleteTopic(topicId: UUID)

    @Query("DELETE FROM topics")
    fun deleteAllTopics()
    @Update
    fun updateTopic(topic: TopicDTO?)

    @Query("SELECT * FROM topics WHERE topic_id = :topicId LIMIT 1")
    fun getTopic(topicId: UUID): LiveData<TopicDTO>

    @Delete
    fun deleteTopic(topic: TopicDTO?)
}