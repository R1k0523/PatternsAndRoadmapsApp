package ru.boringowl.parapp.presentation.repository.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.UserRepository
import ru.boringowl.parapp.presentation.repository.network.parapp.AuthService
import ru.boringowl.parapp.presentation.repository.network.parapp.request.AuthRequestEntity
import ru.boringowl.parapp.presentation.repository.network.parapp.request.LogOutRequest
import ru.boringowl.parapp.presentation.repository.room.dao.UserDAO
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.lang.Exception

class UserRepositoryImpl : UserRepository {
    private val userDAO: UserDAO
    private val userService: AuthService by inject(AuthService::class.java)
    private val prefs by inject(PrefsUtils::class.java)

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        userDAO = db.usersDAO()
    }

    override suspend fun logout() {
        userService.logout(LogOutRequest().apply { userId = Repository.currentUser.value?.userId })
        Repository.setUser(null)
    }

    override fun login(email: String?, password: String) : LiveData<User?> {
        val user: LiveData<User?> = liveData {
            try {
                val response = userService.login(AuthRequestEntity(email!!, password))
                prefs.provideTokensInfo(response.token(), response.refreshToken)
                val userInfo = prefs.getToken()?.let { userService.profile(it) }
                //Repository.setUser(userInfo)
                emit(userInfo)
            } catch (e: Exception) {
                emit(null)
            }
        }
        return user
    }

    override suspend fun register(email: String?, password: String) {
        try {
            userService.register(AuthRequestEntity(email!!, password))
        } catch(e: Exception) {
            throw Exception("Data is invalid")
        }
    }

    override fun oauth(token: String): LiveData<User?> {
        val user: LiveData<User?> = liveData {
            try {
                val response = userService.oauth(token)
                prefs.provideTokensInfo(response.token(), response.refreshToken)
                val userInfo = prefs.getToken()?.let { userService.profile(it) }
                Repository.setUser(userInfo)
                emit(userInfo)
            } catch(e: Exception) {
                emit(null)
            }
        }
        return user
    }
    override suspend fun profile(token: String): User? {
        return try {
            val userInfo = prefs.getToken()?.let { userService.profile(it) }
            Repository.setUser(userInfo)
            userInfo
        } catch(e: Exception) {
            null
        }

    }
//    override fun <T : User> addUser(user: T) {
//        userDAO.addUser(UserDTO(user))
//    }
//
//    override fun <T : User> updateUser(user: T) {
//        userDAO.updateUserInfo(UserDTO(user))
//    }
//
//    override fun <T : User> findUser(email: String?, owner: LifecycleOwner): LiveData<T> {
//        val answer: MutableLiveData<UserDTO> = MutableLiveData<UserDTO>()
//        userDAO.getUserByEmail(email)!!.observe(owner, answer::setValue)
//        return answer as LiveData<T>
//    }
//
//    override fun <T : User> findUser(email: String?, password: String?, owner: LifecycleOwner): LiveData<T?> {
//        val answer: MutableLiveData<UserDTO> = MutableLiveData<UserDTO>()
//        userDAO.getUserByEmailAndPassword(email, password)!!.observe(owner, answer::setValue)
//        return answer as LiveData<T?>
//    }
//
//    override fun <T : User> getUsers(): LiveData<List<T?>>  {
//        return userDAO.allPeople() as LiveData<List<T?>>
//    }
}