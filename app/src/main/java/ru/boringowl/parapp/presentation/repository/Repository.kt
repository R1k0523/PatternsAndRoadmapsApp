package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.network.itnews.NewsRepository
import ru.boringowl.parapp.presentation.repository.network.vacancies.VacancyRepository
import ru.boringowl.parapp.presentation.repository.room.PostsRepositoryImpl
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.lang.Exception

class Repository {
    companion object {
        val patternsRep: PatternsRepository by inject(PatternsRepository::class.java)
        val notesRep: NotesRepository by inject(NotesRepository::class.java)
        val roadmapsRep: RoadmapsRepository by inject(RoadmapsRepository::class.java)
        val newsRepository: NewsRepository by inject(NewsRepository::class.java)
        val vacancyRepository: VacancyRepository by inject(VacancyRepository::class.java)
        val postsRepository: PostsRepository by inject(PostsRepository::class.java)
        val usersRepository: UserRepository by inject(UserRepository::class.java)
        val topicRepository: TopicRepository by inject(TopicRepository::class.java)
        val prefs: PrefsUtils by inject(PrefsUtils::class.java)
        val liveDataMerger: MediatorLiveData<*> = MediatorLiveData<User>()
        private var _currentUser = MutableLiveData<User?>()
        val currentUser: LiveData<User?>
            get() = _currentUser

        fun getUser() {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val token = prefs.getToken()
                    val user = usersRepository.profile(token!!)
                    _currentUser.value = user
                } catch (e: Exception) {
                    _currentUser.value = null
                }
            }
        }

        fun setUser(user: User?) {
            _currentUser.value = user
        }

    }
}