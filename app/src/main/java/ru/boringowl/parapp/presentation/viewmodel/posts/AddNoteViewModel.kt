package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository

class AddNoteViewModel : ViewModel() {
    private val _image = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = _image

    fun setImage(image: String?) {
        _image.value = image
    }

    fun save(note: Note) {
        Repository.notesRep!!.addNote(note)
    }
}