package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel
import ru.boringowl.parapp.presentation.viewmodel.posts.PostsListViewModel

class PostsViewModelFactory(private val topicId: Int): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PostsListViewModel(topicId) as T
}