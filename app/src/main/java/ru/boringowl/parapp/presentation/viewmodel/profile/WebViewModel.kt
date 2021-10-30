package ru.boringowl.parapp.presentation.viewmodel.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent
import ru.boringowl.parapp.MainActivity
import ru.boringowl.parapp.presentation.repository.network.github.GHAuth
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class WebViewModel : ViewModel() {
    private val prefs: PrefsUtils by KoinJavaComponent.inject(PrefsUtils::class.java)
    fun saveToken(url: String, activity: FragmentActivity, view: View) : Boolean {
        val accessTokenFragment = "access_token="
        val accessCodeFragment = "code="
        if (url.contains(accessTokenFragment)) {
            Log.d("token", "URL = $url")

            val accessToken = url.substring(url.indexOf(accessTokenFragment) + accessTokenFragment.length)
            Log.d("token", "accessToken = $accessToken")
            prefs.provideToken(accessToken)
            return true
        } else if (url.contains(accessCodeFragment)) {
            Log.d("token", "URL = $url")
            val accessCode = url.substring(url.indexOf(accessCodeFragment) + accessCodeFragment.length)
            Log.d("token", "accessCode = $accessCode")
            prefs.provideToken(accessCode)
            GHAuth().checkUserInfo(activity, view)
            return true
        }
        return false
    }


}