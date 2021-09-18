package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO

@Dao
interface NotesDAO {

    @Insert
    fun addNote(note: NoteDTO?)

    @Delete
    fun deleteNote(note: NoteDTO?)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NoteDTO>>

    @Query("SELECT * FROM notes WHERE id = :noteId LIMIT 1")
    fun getNote(noteId: Int): NoteDTO
}