package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.patterns.PatternViewModel
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel

class NoteViewModelFactory(private val noteId: Int): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = NoteViewModel(noteId) as T
}