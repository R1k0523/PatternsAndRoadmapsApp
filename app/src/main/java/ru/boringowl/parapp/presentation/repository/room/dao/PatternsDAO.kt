package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import java.util.*

@Dao
interface PatternsDAO {

    @Insert
    fun addPattern(pattern: PatternDTO?)

    @Delete
    fun deletePattern(pattern: PatternDTO?)

    @Query("DELETE FROM patterns WHERE pattern_id = :patternId")
    fun deletePattern(patternId: UUID)

    @Query("DELETE FROM patterns")
    fun deleteAllPatterns()

    @Query("SELECT * FROM patterns")
    fun getAllPatterns(): LiveData<List<PatternDTO>>

    @Query("SELECT * FROM patterns WHERE pattern_id = :patternId LIMIT 1")
    fun getPattern(patternId: UUID): LiveData<PatternDTO>
}