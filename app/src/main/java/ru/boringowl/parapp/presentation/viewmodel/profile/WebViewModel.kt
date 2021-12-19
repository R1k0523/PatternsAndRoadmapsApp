package ru.boringowl.parapp.presentation.viewmodel.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.github.GHAuth
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.repository.network.github.GithubAuthAPI
import ru.boringowl.parapp.presentation.repository.network.github.response.AccessToken
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class WebViewModel : ViewModel() {
    private val prefs: PrefsUtils by inject(PrefsUtils::class.java)
    private val authApi: GithubAuthAPI by inject(GithubAuthAPI::class.java)

    fun getAndSaveTokenFromCode(url: String, lifecycleOwner: LifecycleOwner, view: View) : Boolean {
        val accessCodeFragment = "code="
        if (url.contains(accessCodeFragment)) {
            val accessCode = url.substring(url.indexOf(accessCodeFragment) + accessCodeFragment.length)
            authApi.getToken(accessCode).enqueue(object : Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                    if (response.isSuccessful) {
                        val user = Repository.usersRepository.oauth(response.body()!!.accessToken)
                        user.observe(lifecycleOwner) {
                            if (it != null) {
                                Repository.setUser(it)
                                Navigation.findNavController(view).navigateUp()
                            }
                        }
                        //GHAuth().checkUserInfo(activity, view, true)
                    }
                }

                override fun onFailure(call: Call<AccessToken>, t: Throwable) {}
            })
            return true
        }
        return false
    }

}