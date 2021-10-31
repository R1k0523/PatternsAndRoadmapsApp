package ru.boringowl.parapp.presentation.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class ProfileViewModel : ViewModel() {
    fun logOut() {
        val prefs by inject(PrefsUtils::class.java)
        prefs.deleteToken()
        Repository.setUser(null)
    }

    val user: LiveData<User?> = Repository.currentUser

}