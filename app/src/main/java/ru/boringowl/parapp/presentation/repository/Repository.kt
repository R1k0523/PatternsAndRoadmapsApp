package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsRepository
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyRepository
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class Repository {
    companion object {
        val patternsRep: PatternsRepository by inject(PatternsRepository::class.java)
        val notesRep: NotesRepository by inject(NotesRepository::class.java)
        val roadmapsRep: RoadmapsRepository by inject(RoadmapsRepository::class.java)
        val newsRepository: NewsRepository by inject(NewsRepository::class.java)
        val vacancyRepository: VacancyRepository by inject(VacancyRepository::class.java)
        val usersRepository: UserRepository by inject(UserRepository::class.java)
        val topicRepository: TopicRepository by inject(TopicRepository::class.java)
        private val _currentUser = MutableLiveData<User?>(null)
        val currentUser: LiveData<User?>
            get() = _currentUser

        fun setUser(user: User?) {
            _currentUser.value = user
        }

    }
}