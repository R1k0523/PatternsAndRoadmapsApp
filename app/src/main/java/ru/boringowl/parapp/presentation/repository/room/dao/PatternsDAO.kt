package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO

@Dao
interface PatternsDAO {

    @Insert
    fun addPattern(pattern: PatternDTO?)

    @Delete
    fun deletePattern(pattern: PatternDTO?)

    @Query("SELECT * FROM patterns")
    fun getAllPatterns(): LiveData<List<PatternDTO>>

    @Query("SELECT * FROM patterns WHERE id = :patternId LIMIT 1")
    fun getPattern(patternId: Int): PatternDTO
}