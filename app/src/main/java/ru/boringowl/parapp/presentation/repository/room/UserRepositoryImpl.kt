package ru.boringowl.parapp.presentation.repository.room

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.UserRepository
import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import ru.boringowl.parapp.presentation.repository.room.dao.UserDAO

class UserRepositoryImpl : UserRepository {
    private val userDAO: UserDAO

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        userDAO = db.usersDAO()
    }
    override fun <T : User> addUser(user: T) {
        userDAO.addUser(UserDTO(user))
    }

    override fun <T : User> updateUser(user: T) {
        userDAO.updateUserInfo(UserDTO(user))
    }

    override fun <T : User> findUser(email: String?, owner: LifecycleOwner): LiveData<T> {
        val answer: MutableLiveData<UserDTO> = MutableLiveData<UserDTO>()
        userDAO.getUserByEmail(email)!!.observe(owner, answer::setValue)
        return answer as LiveData<T>
    }

    override fun <T : User> findUser(email: String?, password: String?, owner: LifecycleOwner): LiveData<T> {
        val answer: MutableLiveData<UserDTO> = MutableLiveData<UserDTO>()
        userDAO.getUserByEmailAndPassword(email, password)!!.observe(owner, answer::setValue)
        return answer as LiveData<T>
    }
}