package ru.boringowl.parapp.presentation.repository.network.github

import retrofit2.Call
import retrofit2.http.*
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse

interface GithubAPI {

    @GET("user")
    @Headers("Accept: application/vnd.github.v3+json")
    fun getUser(@Header("Authorization") token: String): Call<UserResponse>

}