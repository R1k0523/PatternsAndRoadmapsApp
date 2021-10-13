package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.presentation.repository.Repository

class AddNoteViewModel : ViewModel() {
    private val _image = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = _image

    val sections = MutableLiveData<ArrayList<NoteSection>>()

    init {
        sections.value = arrayListOf()
    }

    fun setImage(image: String?) {
        _image.value = image
    }

    fun save(note: Note) {
        viewModelScope.launch {
            Repository.notesRep.addNote(note)
        }

    }
}