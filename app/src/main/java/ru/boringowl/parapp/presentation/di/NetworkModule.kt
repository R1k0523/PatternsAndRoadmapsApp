package ru.boringowl.parapp.presentation.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.repository.network.github.GithubAuthAPI
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsAPI
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsService
import ru.boringowl.parapp.presentation.repository.network.parapp.*
import ru.boringowl.parapp.presentation.repository.network.vacancies.HeadHunterAPI
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyService
import ru.boringowl.parapp.presentation.utils.PrefsUtils


val networkModule = module {

    single { okhttpClient() }

    single(named("retro_news")) { retrofit(get(), BuildConfig.NEWS_BASE_URL) }
    single { apiNews(get(named("retro_news"))) }
    single { newsService(get()) }

    single(named("retro_hh")) { retrofit(get(), BuildConfig.HH_BASE_URL) }
    single { apiVacancy(get(named("retro_hh"))) }
    single { vacancyService(get()) }


    single(named("retro_gh")) { retrofit(get(), BuildConfig.GH_BASE_URL) }
    single { apiGithub(get(named("retro_gh"))) }

    single(named("retro_gh_auth")) { retrofit(get(), BuildConfig.BASE_LOGIN_URL) }
    single { apiGithubAuth(get(named("retro_gh_auth"))) }

    single(named("retro_parapp")) { retrofit(get(), BuildConfig.MAIN_LINK) }
    single { apiParapp(get(named("retro_parapp"))) }
    single { authService(get()) }
    single { fileService(get()) }
    single { patternsService(get()) }
    single { postsService(get()) }
    single { topicService(get()) }
}

fun okhttpClient() : OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}

fun retrofit(okHttpClient: OkHttpClient, url: String) : Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
fun apiParapp(retrofit: Retrofit) : ParappDataAPI = retrofit.create(ParappDataAPI::class.java)
fun authService(api: ParappDataAPI) : AuthService  = AuthService(api)
fun fileService(api: ParappDataAPI) : FileService = FileService(api)
fun patternsService(api: ParappDataAPI) : PatternsService  = PatternsService(api)
fun postsService(api: ParappDataAPI) : PostsService = PostsService(api)
fun topicService(api: ParappDataAPI) : TopicService = TopicService(api)

fun apiNews(retrofit: Retrofit) : NewsAPI = retrofit.create(NewsAPI::class.java)
fun apiVacancy(retrofit: Retrofit) : HeadHunterAPI = retrofit.create(HeadHunterAPI::class.java)
fun apiGithub(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
fun apiGithubAuth(retrofit: Retrofit): GithubAuthAPI = retrofit.create(GithubAuthAPI::class.java)

fun newsService(api: NewsAPI) : NewsService = NewsService(api)
fun vacancyService(api: HeadHunterAPI) : VacancyService = VacancyService(api)

