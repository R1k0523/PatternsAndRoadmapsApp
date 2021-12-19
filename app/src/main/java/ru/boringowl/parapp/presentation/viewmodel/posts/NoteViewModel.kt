package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.vacancies.response.Vacancy
import java.util.*

class NoteViewModel(noteId: UUID) : ViewModel() {
    private val _note = Repository.postsRepository.getNote(noteId)
    val note: LiveData<Note?>
        get() = _note
    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies


    fun getVacancies() {
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