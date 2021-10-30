package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.room.Dao
import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    fun addUser(user: UserDTO?)

    @Query("SELECT * FROM users_table WHERE email = :email")
    fun getUserByEmail(email: String?): LiveData<UserDTO?>?

    @Query("SELECT * FROM users_table WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String?, password: String?): LiveData<UserDTO?>?

    @get:Query("SELECT * FROM users_table")
    val allPeople: LiveData<List<UserDTO?>?>?

    @Update
    fun updateUserInfo(user: UserDTO?)
}