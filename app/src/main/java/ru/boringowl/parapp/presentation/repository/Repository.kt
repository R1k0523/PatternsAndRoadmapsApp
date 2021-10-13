package ru.boringowl.parapp.presentation.repository

import android.app.Application
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.presentation.repository.mock.NotesMockRepository
import ru.boringowl.parapp.presentation.repository.mock.PatternsMockRepository
import ru.boringowl.parapp.presentation.repository.mock.RoadmapsMockRepository
import ru.boringowl.parapp.presentation.repository.room.NotesRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.PatternsRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.RoadmapsRepositoryImpl

class Repository {
    companion object {
        val patternsRep: PatternsRepository by inject(PatternsRepository::class.java)
        val notesRep: NotesRepository by inject(NotesRepository::class.java)
        val roadmapsRep: RoadmapsRepository by inject(RoadmapsRepository::class.java)
        val newsRepository: NewsRepository by inject(NewsRepository::class.java)


    }
}