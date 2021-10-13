package ru.boringowl.parapp.presentation.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsAPI
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsService
import ru.boringowl.parapp.presentation.repository.network.vacancies.HeadHunterAPI
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyService

val networkModule = module {
    single { headerInterceptor() }
    single { okhttpClient() }

    single(named("retro1")) { retrofit(get(), BuildConfig.NEWS_BASE_URL) }
    single { apiNewsService(get(named("retro1"))) }
    single { createNewsService(get()) }

    single(named("retro2")) { retrofit(get(), BuildConfig.HH_BASE_URL) }
    single { apiVacancyService(get(named("retro2"))) }
    single { createVacancyService(get()) }
}

fun createNewsService(
    api: NewsAPI
) : NewsService = NewsService(api)

fun apiNewsService(
    retrofit: Retrofit
) : NewsAPI =
    retrofit.create(NewsAPI::class.java)

fun retrofit(
    okHttpClient: OkHttpClient,
    url: String
) : Retrofit =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun createVacancyService(
    api: HeadHunterAPI
) : VacancyService = VacancyService(api)

fun apiVacancyService(
    retrofit: Retrofit
) : HeadHunterAPI =
    retrofit.create(HeadHunterAPI::class.java)


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