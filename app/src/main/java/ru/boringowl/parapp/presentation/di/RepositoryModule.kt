package ru.boringowl.parapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsRepository
import ru.boringowl.parapp.presentation.repository.NotesRepository
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.presentation.repository.RoadmapsRepository
import ru.boringowl.parapp.presentation.repository.UserRepository
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsService
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyRepository
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyService
import ru.boringowl.parapp.presentation.repository.room.*
import ru.boringowl.parapp.presentation.utils.PrefsUtils

val repositoryModule = module {
    single { provideNewsRepository(get()) }
    single { provideVacancyRepository(get()) }
    single { provideGson() }
    single { providePatternsRepository() }
    single { provideNotesRepository() }
    single { provideRoadmapsRepository() }
    single { provideUsersRepository() }
    single { provideDatabase(this.androidContext()) }
    single { providePrefs(this.androidContext()) }
}

fun provideNewsRepository(
    newsService: NewsService
) : NewsRepository = NewsRepository(newsService)
fun provideVacancyRepository(
    vacancyService: VacancyService
) : VacancyRepository = VacancyRepository(vacancyService)
fun providePatternsRepository(): PatternsRepository = PatternsRepositoryImpl()
fun provideNotesRepository(): NotesRepository = NotesRepositoryImpl()
fun provideRoadmapsRepository(): RoadmapsRepository = RoadmapsRepositoryImpl()
fun provideUsersRepository(): UserRepository = UserRepositoryImpl()

fun provideGson(): Gson = Gson()

fun provideDatabase(context: Context): MyDatabase =
    Room.databaseBuilder(
        context.applicationContext,
        MyDatabase::class.java,
        "parapp_db1"
    )
    .fallbackToDestructiveMigration()
    .allowMainThreadQueries()
    .build()

fun providePrefs(context: Context) : PrefsUtils = PrefsUtils(context)