package ru.boringowl.parapp.presentation.viewmodel.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.presentation.repository.Repository

class AddRoadmapViewModel : ViewModel() {
    private val _image = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = _image

    fun setImage(image: String?) {
        _image.value = image
    }

    fun save(roadmap: Roadmap) {
        viewModelScope.launch {
            Repository.roadmapsRep.addRoadmap(roadmap)
        }
    }
}