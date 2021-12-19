package ru.boringowl.parapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.boringowl.parapp.presentation.viewmodel.posts.RoadmapViewModel
import java.util.*

class RoadmapViewModelFactory(private val roadmapId: String): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = RoadmapViewModel(UUID.fromString(roadmapId)) as T
}