package ru.boringowl.parapp.presentation.viewmodel.profile

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class AuthViewModel : ViewModel() {
    fun checkAll(owner: LifecycleOwner) {
        Repository.usersRepository.getUsers<User>().observe(owner) {
            Log.d("kekes42", it.toString())
        }
    }
    fun auth(login: String, password: String, owner: LifecycleOwner): LiveData<User?> {
        return Repository.usersRepository.findUser(login, password, owner)
    }
    fun setUser(user: User) {
        Repository.setUser(user)
    }
}