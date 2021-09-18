package ru.boringowl.parapp.presentation.viewmodel.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesListViewModel : ViewModel() {
    private val _isFABOpen = MutableLiveData<Boolean>()
    val isFABOpen: LiveData<Boolean>
        get() = _isFABOpen

    init {
        _isFABOpen.value = false
    }

    fun setFABOpen() {
        _isFABOpen.value = true
    }
    fun setFABClosed() {
        _isFABOpen.value = false
    }
}