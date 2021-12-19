package ru.boringowl.parapp.presentation.repository.network.github

import retrofit2.Call
import retrofit2.http.*
import ru.boringowl.parapp.BuildConfig.*
import ru.boringowl.parapp.presentation.repository.network.github.response.AccessToken
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse

interface GithubAPI {
    @GET("user")
    fun getUser(@Header("Authorization") token: String): Call<UserResponse>
}