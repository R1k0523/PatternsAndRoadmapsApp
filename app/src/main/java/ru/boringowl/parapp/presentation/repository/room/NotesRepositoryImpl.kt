package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.NotesRepository
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.room.dao.NotesDAO

class NotesRepositoryImpl(application: Application) : NotesRepository {
    private var notesDAO: NotesDAO
    private var allPatterns: LiveData<List<NoteDTO>>

    init {
        val db: MyDatabase = MyDatabase.getInstance(application)
        notesDAO = db.notesDAO()
        allPatterns =  notesDAO.getAllNotes()
    }

    override fun <T : Note> getAllNotes(): LiveData<List<T>> {
        Log.d("Notes", "got")

        return allPatterns as LiveData<List<T>>
    }

    override fun <T : Note> addNote(note: T) {
        Log.d("Notes", "added")
        MyDatabase.databaseWriteExecutor.execute { notesDAO.addNote(NoteDTO(note)) }
    }

    override fun <T : Note> getNote(noteId: Int): T {
        return notesDAO.getNote(noteId) as T
    }

    override fun <T : Note> deleteNote(note: T) {
        MyDatabase.databaseWriteExecutor.execute { notesDAO.deleteNote(note as NoteDTO) }
    }

}