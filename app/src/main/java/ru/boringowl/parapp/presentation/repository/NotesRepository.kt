package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.notes.Note
import java.util.*

interface NotesRepository {
    fun <T : Note> getAllNotes(): LiveData<List<T>>
    fun <T : Note> getAllNotes(topicId: UUID): LiveData<List<T>>
    suspend fun <T : Note> addNote(note: T)
    fun <T : Note> getNote(noteId: UUID): T
    suspend fun <T : Note> deleteNote(note: T)
}