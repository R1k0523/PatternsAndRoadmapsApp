package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import java.util.*

@Dao
interface NotesDAO {

    @Insert
    fun addNote(note: NoteDTO?)

    @Query("SELECT EXISTS(SELECT * FROM notes WHERE post_id = :id)")
    fun isRowIsExist(id : UUID) : Boolean

    @Delete
    fun deleteNote(note: NoteDTO?)

    @Query("DELETE FROM notes WHERE post_id = :noteId")
    fun deleteNote(noteId: UUID)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NoteDTO>>

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE post_id = :noteId LIMIT 1")
    fun getNote(noteId: String): LiveData<NoteDTO?>


    @Query("SELECT * FROM notes WHERE topic = :topicId")
    fun getAllNotes(topicId: String): LiveData<List<NoteDTO>>
}