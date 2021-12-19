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
import ru.boringowl.parapp.presentation.repository.network.parapp.request.AuthRequestEntity
import ru.boringowl.parapp.presentation.repository.network.parapp.request.LogOutRequest
import ru.boringowl.parapp.presentation.repository.network.parapp.request.TokenRefreshRequest
import ru.boringowl.parapp.presentation.repository.network.parapp.response.*
import java.util.*

interface ParappDataAPI {
    // AUTH
    @POST("/loggingout")
    @Headers("Accept: application/json")
    suspend fun logout(@Body request: LogOutRequest) : Response<GenericResponse>
    @POST("/login")
    @Headers("Accept: application/json")
    suspend fun login(@Body request: AuthRequestEntity) : Response<JwtResponse>
    @POST("/register")
    @Headers("Accept: application/json")
    suspend fun register(@Body request: AuthRequestEntity) : Response<GenericResponse>
    @POST("/oauth")
    @Headers("Accept: application/json")
    suspend fun oauth(@Query("token") token: String) : Response<JwtResponse>
    @POST("/refreshtoken")
    @Headers("Accept: application/json")
    suspend fun refresh(@Body request: TokenRefreshRequest) : Response<TokenRefreshResponse>
    @POST("/profile")
    @Headers("Accept: application/json")
    suspend fun profile(@Header("Authorization") token: String) : Response<User>

    //TOPICS
    @POST("/api/topic")
    @Headers("Accept: application/json")
    suspend fun createTopic(@Header("Authorization") token: String, @Body topic: Topic) : Response<Topic>
    @PUT("/api/topic")
    @Headers("Accept: application/json")
    suspend fun updateTopic(@Header("Authorization") token: String, @Body topic: Topic) : Response<Topic>
    @DELETE("/api/topic/{id}")
    @Headers("Accept: application/json")
    suspend fun deleteTopic(@Header("Authorization") token: String, @Path("id") id: UUID) : Response<GenericResponse>
    @GET("/api/topic/{id}")
    @Headers("Accept: application/json")
    suspend fun getTopic(@Path("id") id: UUID) : Response<Topic>
    @GET("/api/topic")
    @Headers("Accept: application/json")
    suspend fun getAllTopics() : Response<ListResponse<Topic>>

    //PATTERNS
    @POST("api/pattern")
    @Headers("Accept: application/json")
    suspend fun createPattern(@Header("Authorization") token: String, @Body topic: Pattern) : Response<Pattern>
    @PUT("/api/pattern")
    @Headers("Accept: application/json")
    suspend fun updatePattern(@Header("Authorization") token: String, @Body topic: Pattern) : Response<Pattern>
    @DELETE("/api/pattern/{id}")
    @Headers("Accept: application/json")
    suspend fun deletePattern(@Header("Authorization") token: String, @Path("id") id: UUID) : Response<GenericResponse>
    @GET("/api/pattern/{id}")
    @Headers("Accept: application/json")
    suspend fun getPattern(@Path("id") id: UUID) : Response<Pattern>
    @GET("api/pattern")
    @Headers("Accept: application/json")
    suspend fun getAllPatterns() : Response<ListResponse<Pattern>>

    //POSTS
    @POST("/api/note")
    @Headers("Accept: application/json")
    suspend fun createNote(@Header("Authorization") token: String, @Body note: NoteResponse) : Response<NoteResponse>
    @PUT("/api/note")
    @Headers("Accept: application/json")
    suspend fun updateNote(@Header("Authorization") token: String, @Body note: NoteResponse) : Response<NoteResponse>

    @POST("/api/roadmap")
    @Headers("Accept: application/json")
    suspend fun createRoadmap(@Header("Authorization") token: String, @Body roadmap: RoadmapResponse) : Response<RoadmapResponse>
    @PUT("/api/roadmap")
    @Headers("Accept: application/json")
    suspend fun updateRoadmap(@Header("Authorization") token: String, @Body roadmap: RoadmapResponse) : Response<RoadmapResponse>

    @DELETE("/api/post/{id}")
    @Headers("Accept: application/json")
    suspend fun deletePost(@Header("Authorization") token: String, @Path("id") id: UUID) : Response<GenericResponse>
    @GET("/api/post/{id}")
    @Headers("Accept: application/json")
    suspend fun getRoadmap(@Path("id") id: UUID) : Response<RoadmapResponse>
    @GET("/api/post/{id}")
    @Headers("Accept: application/json")
    suspend fun getNote(@Path("id") id: UUID) : Response<NoteResponse>
    @GET("/api/bytopic/{id}")
    @Headers("Accept: application/json")
    suspend fun getPostsByTopic(@Path("id") id: UUID) : Response<ListResponse<Post>>

    //FILES
    @Multipart
    @POST("/uploadMultipleFiles")
    @Headers("Accept: multipart/form-data")
    suspend fun uploadFiles(@Header("Authorization") token: String,
                            @Part("files")  files: Array<MultipartBody.Part>) :
            Response<List<UploadFileResponse>>
    @POST("/uploadFile")
    @Headers("Accept: multipart/form-data")
    suspend fun uploadFile(@Header("Authorization") token: String,
                           @Part("file")  file: MultipartBody.Part) :
            Response<UploadFileResponse>
    @GET("/downloadFile/{fileName}")
    @Headers("Accept: multipart/form-data")
    suspend fun uploadFiles(@Path("fileName") fileName: String) :
            Response<ResponseBody>

}