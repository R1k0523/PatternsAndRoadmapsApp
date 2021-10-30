package ru.boringowl.parapp.presentation.repository.network.github

import retrofit2.Call
import retrofit2.http.*
import ru.boringowl.parapp.BuildConfig.*
import ru.boringowl.parapp.presentation.repository.network.github.response.AccessToken
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse

interface GithubAuthAPI {

    @Headers("Accept: application/json")
    @POST("access_token")
    fun getToken(@Query("code") code: String,
                 @Query("client_id") clientId: String = CLIENT_ID,
                 @Query("client_secret") clientSecret: String = CLIENT_SECRET,
                 @Query("redirect_uri") redirectUri: String = CLIENT_REDIRECT,
    ): Call<AccessToken>


}