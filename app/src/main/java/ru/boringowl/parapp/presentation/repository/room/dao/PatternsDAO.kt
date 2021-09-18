package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternInfoDTO

@Dao
interface PatternsDAO {

    @Insert
    fun addPatternInfo(party: PatternInfoDTO?)

    @Delete
    fun deletePatternInfo(party: PatternInfoDTO?)

    @Query("SELECT * FROM patterns")
    fun getAllPatternInfo(): LiveData<List<PatternInfoDTO>>

    @Query("SELECT * FROM patterns WHERE id = :patternId LIMIT 1")
    fun getPatternInfo(patternId: Int): PatternInfoDTO
}