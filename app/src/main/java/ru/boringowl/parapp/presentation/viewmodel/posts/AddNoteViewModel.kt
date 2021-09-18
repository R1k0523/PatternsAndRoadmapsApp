package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository

class AddNoteViewModel : ViewModel() {

    fun save(note: Note) {
        Repository.notesRep!!.addNote(note)
    }
}