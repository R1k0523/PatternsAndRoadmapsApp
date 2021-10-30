package ru.boringowl.parapp.presentation.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository

class ProfileViewModel : ViewModel() {

    val user: LiveData<User?> = Repository.currentUser

}