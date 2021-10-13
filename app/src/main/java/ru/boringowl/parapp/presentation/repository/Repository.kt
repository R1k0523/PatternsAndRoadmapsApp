package ru.boringowl.parapp.presentation.repository

import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsRepository
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyRepository

class Repository {
    companion object {
        val patternsRep: PatternsRepository by inject(PatternsRepository::class.java)
        val notesRep: NotesRepository by inject(NotesRepository::class.java)
        val roadmapsRep: RoadmapsRepository by inject(RoadmapsRepository::class.java)
        val newsRepository: NewsRepository by inject(NewsRepository::class.java)
        val vacancyRepository: VacancyRepository by inject(VacancyRepository::class.java)


    }
}