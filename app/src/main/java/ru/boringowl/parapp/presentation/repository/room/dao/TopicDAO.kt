package ru.boringowl.parapp.presentation.repository.room.dao

import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import androidx.lifecycle.LiveData
import androidx.room.*
import ru.boringowl.parapp.presentation.repository.model.notes.TopicDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO

@Dao
interface TopicDAO {
    @Insert
    fun addTopic(topic: TopicDTO?)

    @Query("SELECT * FROM topics WHERE creator = :email")
    fun getTopicByEmail(email: String?): LiveData<List<TopicDTO?>?>?

    @Query("SELECT * FROM topics")
    fun getAllTopics(): LiveData<List<TopicDTO>>

    @Update
    fun updateTopic(topic: TopicDTO?)

    @Query("SELECT * FROM topics WHERE id = :topicId LIMIT 1")
    fun getTopic(topicId: Int): TopicDTO

    @Delete
    fun deleteTopic(topic: TopicDTO?)
}