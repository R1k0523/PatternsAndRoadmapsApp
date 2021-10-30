package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import ru.boringowl.parapp.presentation.repository.room.dao.UserDAO

interface UserRepository {
    fun <T : User> addUser(user: T)
    fun <T : User> updateUser(user: T)
    fun <T : User> findUser(email: String?, owner: LifecycleOwner): LiveData<T>
    fun <T : User> findUser(email: String?, password: String?, owner: LifecycleOwner): LiveData<T>
}