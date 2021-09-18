package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository

class RoadmapViewModel(roadmapId: Int) : ViewModel() {
    private val _roadmap = MutableLiveData<Roadmap>()
    val roadmap: LiveData<Roadmap>
        get() = _roadmap

    init {
        _roadmap.value = Repository.roadmapsRep?.getRoadmap(roadmapId)
    }
}