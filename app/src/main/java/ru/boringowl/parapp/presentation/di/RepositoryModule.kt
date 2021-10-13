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
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsService
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyRepository
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyService
import ru.boringowl.parapp.presentation.repository.room.MyDatabase
import ru.boringowl.parapp.presentation.repository.room.NotesRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.PatternsRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.RoadmapsRepositoryImpl

val repositoryModule = module {
    single { provideNewsRepository(get()) }
    single { provideVacancyRepository(get()) }
    single { provideGson() }
    single { providePatternsRepository() }
    single { provideNotesRepository() }
    single { provideRoadmapsRepository() }
    single { provideDatabase(this.androidContext()) }
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