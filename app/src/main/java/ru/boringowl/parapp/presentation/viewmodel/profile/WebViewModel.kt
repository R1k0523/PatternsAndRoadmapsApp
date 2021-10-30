package ru.boringowl.parapp.presentation.viewmodel.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.boringowl.parapp.presentation.repository.network.github.GHAuth
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.repository.network.github.GithubAuthAPI
import ru.boringowl.parapp.presentation.repository.network.github.response.AccessToken
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class WebViewModel : ViewModel() {
    private val prefs: PrefsUtils by inject(PrefsUtils::class.java)
    private val authApi: GithubAuthAPI by inject(GithubAuthAPI::class.java)
    private val api: GithubAPI by inject(GithubAPI::class.java)

    fun getAndSaveTokenFromCode(url: String, activity: FragmentActivity, view: View) : Boolean {
        val accessCodeFragment = "code="
        if (url.contains(accessCodeFragment)) {
            val accessCode = url.substring(url.indexOf(accessCodeFragment) + accessCodeFragment.length)
            authApi.getToken(accessCode).enqueue(object : Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                    prefs.provideToken("token "+ response.body()!!.accessToken)
                    GHAuth().checkUserInfo(activity, view)
                }

                override fun onFailure(call: Call<AccessToken>, t: Throwable) {}
            })
            return true
        }
        return false
    }


}