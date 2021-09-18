package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository

class AddRoadmapViewModel : ViewModel() {

    fun save(roadmap: Roadmap) {
        Repository.roadmapsRep!!.addRoadmap(roadmap)
    }
}