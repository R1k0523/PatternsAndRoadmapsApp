package ru.boringowl.parapp.presentation.repository.network.parapp

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.parapp.request.AuthRequestEntity
import ru.boringowl.parapp.presentation.repository.network.parapp.request.LogOutRequest
import ru.boringowl.parapp.presentation.repository.network.parapp.request.TokenRefreshRequest
import ru.boringowl.parapp.presentation.repository.network.parapp.response.*
import java.util.*

class AuthService(private val api: ParappDataAPI) : BaseService() {

    suspend fun logout(request: LogOutRequest) : GenericResponse {
        return when(val result = createCall { api.logout(request)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun login(request: AuthRequestEntity) : JwtResponse {
        return when(val result = createCall { api.login(request)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun register(request: AuthRequestEntity) : GenericResponse {
        return when(val result = createCall { api.register(request)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun oauth(token: String) : JwtResponse {
        return when(val result = createCall { api.oauth(token)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }


    suspend fun refresh(request: TokenRefreshRequest) : TokenRefreshResponse {

        return when(val result = createCall { api.refresh(request)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }

    suspend fun profile(token: String) : User {
        return when(val result = createCall { api.profile(token)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
}