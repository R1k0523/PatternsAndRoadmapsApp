package ru.boringowl.parapp.presentation.repository

import android.app.Application
import ru.boringowl.parapp.presentation.repository.mock.NotesMockBase
import ru.boringowl.parapp.presentation.repository.mock.PatternsMockBase
import ru.boringowl.parapp.presentation.repository.mock.RoadmapsMockBase
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
                patternsRep = PatternsMockBase()
            }
            if (notesRep == null) {
                notesRep = NotesMockBase()
            }
            if (roadmapsRep == null) {
                roadmapsRep = RoadmapsMockBase()
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