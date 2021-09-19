package ru.boringowl.parapp.presentation.repository

import android.app.Application
import ru.boringowl.parapp.presentation.repository.mock.NotesMockRepository
import ru.boringowl.parapp.presentation.repository.mock.PatternsMockRepository
import ru.boringowl.parapp.presentation.repository.mock.RoadmapsMockRepository
import ru.boringowl.parapp.presentation.repository.room.NotesRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.PatternsRepositoryImpl
import ru.boringowl.parapp.presentation.repository.room.RoadmapsRepositoryImpl

class Repository {
    companion object {
        var patternsRep: PatternsRepository? = null
        var notesRep: NotesRepository? = null
        var roadmapsRep: RoadmapsRepository? = null

        fun initRepository() {
            if (patternsRep == null) {
                patternsRep = PatternsMockRepository()
            }
            if (notesRep == null) {
                notesRep = NotesMockRepository()
            }
            if (roadmapsRep == null) {
                roadmapsRep = RoadmapsMockRepository()
            }
        }
        fun initRepository(application: Application) {
            if (patternsRep == null) {
                patternsRep = PatternsRepositoryImpl(application)
            }
            if (notesRep == null) {
                notesRep = NotesRepositoryImpl(application)
            }
            if (roadmapsRep == null) {
                roadmapsRep = RoadmapsRepositoryImpl(application)
            }
        }
    }
}