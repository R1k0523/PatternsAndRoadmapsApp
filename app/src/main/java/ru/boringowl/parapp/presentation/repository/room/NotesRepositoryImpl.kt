package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.NotesRepository
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.room.dao.NotesDAO

class NotesRepositoryImpl : NotesRepository {
    private var notesDAO: NotesDAO
    private var allPatterns: LiveData<List<NoteDTO>>

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        notesDAO = db.notesDAO()
        allPatterns =  notesDAO.getAllNotes()
    }

    override fun <T : Note> getAllNotes(): LiveData<List<T>> {
        return allPatterns as LiveData<List<T>>
    }

    override fun <T : Note> getAllNotes(topicId: Int): LiveData<List<T>> {
        return notesDAO.getAllNotes(topicId) as LiveData<List<T>>
    }

    override suspend fun <T : Note> addNote(note: T) {
        notesDAO.addNote(NoteDTO(note))
    }

    override fun <T : Note> getNote(noteId: Int): T {
        return notesDAO.getNote(noteId) as T
    }

    override suspend fun <T : Note> deleteNote(note: T) {
        notesDAO.deleteNote(note as NoteDTO)
    }

}