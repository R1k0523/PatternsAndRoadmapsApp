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
        val regex = Regex("[A-Z][A-z]+")
        val query = arrayListOf<String>()
        var text = _note.value!!.categories() + " "
        _note.value?.sections?.forEach { it ->
            text += it.description
        }
        regex.findAll(text).iterator().forEach {
            if (it.value !in query)
            query.add(it.value)
        }
        text = query.joinToString(" OR ")
        viewModelScope.launch {
            val response = Repository.vacancyRepository.getVacancies(text)
            Log.d("VACA", response.items.toString())
            if (response.items != null)
                _vacancies.value = response.items!!
        }

    }
}