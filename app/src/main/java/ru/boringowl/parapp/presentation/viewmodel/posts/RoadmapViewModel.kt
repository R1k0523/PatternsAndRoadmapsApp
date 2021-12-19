package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository
import java.util.*

class RoadmapViewModel(roadmapId: UUID) : ViewModel() {
    private val _roadmap = Repository.postsRepository.getRoadmap(roadmapId)
    val roadmap: LiveData<Roadmap?>
        get() = _roadmap
}