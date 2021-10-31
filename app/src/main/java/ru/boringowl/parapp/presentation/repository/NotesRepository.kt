package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.notes.Note

interface NotesRepository {
    fun <T : Note> getAllNotes(): LiveData<List<T>>
    fun <T : Note> getAllNotes(topicId: Int): LiveData<List<T>>
    suspend fun <T : Note> addNote(note: T)
    fun <T : Note> getNote(noteId: Int): T
    suspend fun <T : Note> deleteNote(note: T)
}