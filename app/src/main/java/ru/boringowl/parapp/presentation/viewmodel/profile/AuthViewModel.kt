package ru.boringowl.parapp.presentation.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import java.lang.Exception

class AuthViewModel : ViewModel() {
    fun auth(login: String, password: String): LiveData<User?> {
        return try {
            Repository.usersRepository.login(login, password)
        } catch (e: Exception) {
            MutableLiveData<User?>(null)
        }
    }
    fun setUser(user: User) {
        Repository.setUser(user)
    }
}