package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository

class NoteViewModel(noteId: Int) : ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    init {
        _note.value = Repository.notesRep?.getNote(noteId)
    }
}