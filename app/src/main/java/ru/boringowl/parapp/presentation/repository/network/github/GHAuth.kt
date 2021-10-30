package ru.boringowl.parapp.presentation.repository.network.github

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse
import retrofit2.Callback
import retrofit2.Response
import ru.boringowl.parapp.MainActivity
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.utils.PrefsUtils


class GHAuth {
    private val api by inject(GithubAPI::class.java)
    private val prefs by inject(PrefsUtils::class.java)
    fun checkUserInfo(activity: FragmentActivity, view: View) {
        if (prefs.getToken() != null)
        api.getUser("e1477fe29307b0f91983").enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    if (Repository.currentUser.value != null) {
                        val body = response.body()!!
                        Repository.setUser(User(
                            body.bio,
                            body.login,
                            body.company,
                            body.email,
                            body.url,
                            body.avatarUrl,
                            body.name,
                            body.location,
                            body.blog
                        ))

                        Repository.usersRepository.findUser<User>(
                            Repository.currentUser.value!!.email, activity
                        ).observe(activity) {
                            if (it == null) {
                                Repository.usersRepository.addUser(Repository.currentUser.value!!)
                            } else {
                                it.apply {
                                    bio = response.body()!!.bio
                                    login = response.body()!!.login
                                    company = response.body()!!.company
                                    email = response.body()!!.email
                                    url = response.body()!!.url
                                    avatarUrl = response.body()!!.avatarUrl
                                    name = response.body()!!.name
                                    location = response.body()!!.location
                                    blog = response.body()!!.blog
                                }
                                Repository.usersRepository.updateUser(it)
                            }

                        }
                    } else {
                        Repository.setUser(null)
                        val prefs: PrefsUtils by inject(PrefsUtils::class.java)
                        Log.d("kekes41", response.raw().toString())
                        prefs.deleteToken()
                    }
                } else {
                    Repository.setUser(null)
                    val prefs: PrefsUtils by inject(PrefsUtils::class.java)
                    Log.d("kekes42", response.raw().toString())
                    prefs.deleteToken()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(activity,
                    "Произошла ошибка, повторите попытку снова",
                    Toast.LENGTH_LONG
                ).show()
                Navigation.findNavController(view).navigateUp()
            }
        })
    }

}