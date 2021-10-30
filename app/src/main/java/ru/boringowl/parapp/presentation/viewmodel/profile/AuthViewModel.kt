package ru.boringowl.parapp.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class AuthViewModel : ViewModel() {
    private val loginApi by inject(GithubAPI::class.java)
    private val prefs by inject(PrefsUtils::class.java)
}