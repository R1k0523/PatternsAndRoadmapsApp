package ru.boringowl.parapp.presentation.viewmodel.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.Vacancy

class NoteViewModel(noteId: Int) : ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note
    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    init {
        _note.value = Repository.notesRep.getNote(noteId)
        val text = _note.value!!.getKeyWords()
        viewModelScope.launch {
            try {
                val response = Repository.vacancyRepository.getVacancies(text)
                if (response.items != null)
                    _vacancies.value = response.items!!
            } catch (e: Exception) {
                _vacancies.value = listOf()
            }
        }

    }
}