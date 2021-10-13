package ru.boringowl.parapp.presentation.repository.network.itnews

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.Utils
import ru.boringowl.parapp.presentation.repository.network.itnews.response.NewsResponse


interface NewsAPI {

    @GET("v2/everything")
    suspend fun news(
        @Query("q") query: String = "github",
        @Query("language") lang: String = "ru",
        @Query("from") from: String = Utils.yesterdayDate(),
        @Query("to") to: String = Utils.currentDate(),
        @Query("sortBy") sortBy: String = "popularity",
        @Query("pageSize") pageSize: Int = 10,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,

        ): Response<NewsResponse>

}