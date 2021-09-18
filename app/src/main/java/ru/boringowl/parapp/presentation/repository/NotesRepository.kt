package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.notes.notelinks.Note

interface NotesRepository {
    fun <T : Note> getAllNotes(): LiveData<List<T>>
    fun <T : Note> addNote(note: T)
    fun <T : Note> getNote(noteId: Int): T
    fun <T : Note> deleteNote(note: T)
}