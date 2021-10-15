package ru.boringowl.parapp.presentation.di

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
    single { okhttpClient() }

    single(named("retro_news")) { retrofit(get(), BuildConfig.NEWS_BASE_URL) }
    single { apiNews(get(named("retro_news"))) }
    single { newsService(get()) }

    single(named("retro_hh")) { retrofit(get(), BuildConfig.HH_BASE_URL) }
    single { apiVacancy(get(named("retro_hh"))) }
    single { vacancyService(get()) }
}

fun okhttpClient() : OkHttpClient = OkHttpClient.Builder().build()

fun retrofit(okHttpClient: OkHttpClient, url: String) : Retrofit =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun apiNews(retrofit: Retrofit) : NewsAPI = retrofit.create(NewsAPI::class.java)
fun apiVacancy(retrofit: Retrofit) : HeadHunterAPI = retrofit.create(HeadHunterAPI::class.java)

fun newsService(api: NewsAPI) : NewsService = NewsService(api)
fun vacancyService(api: HeadHunterAPI) : VacancyService = VacancyService(api)




