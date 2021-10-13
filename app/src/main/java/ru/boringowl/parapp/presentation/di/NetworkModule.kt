package ru.boringowl.parapp.presentation.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsAPI
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsService

val networkModule = module {
    single { headerInterceptor() }
    single { okhttpClient() }
    single { retrofit(get()) }
    single { apiService(get()) }
    single { createMovieAppService(get()) }
}

fun createMovieAppService(
    api: NewsAPI
) : NewsService = NewsService(api)

fun apiService(
    retrofit: Retrofit
) : NewsAPI =
    retrofit.create(NewsAPI::class.java)

fun retrofit(
    okHttpClient: OkHttpClient
) : Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.NEWS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient() : OkHttpClient =
    OkHttpClient.Builder()
        .build()

fun headerInterceptor() : Interceptor =
    Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }