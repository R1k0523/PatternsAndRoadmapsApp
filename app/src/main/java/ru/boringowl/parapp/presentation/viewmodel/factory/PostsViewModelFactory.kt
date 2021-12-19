package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel
import ru.boringowl.parapp.presentation.viewmodel.posts.PostsListViewModel
import java.util.*

class PostsViewModelFactory(private val topicId: String): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PostsListViewModel(UUID.fromString(topicId)) as T
}