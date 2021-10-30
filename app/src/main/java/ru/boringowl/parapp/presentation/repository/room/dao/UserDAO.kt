package ru.boringowl.parapp.presentation.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.boringowl.parapp.presentation.repository.model.user.UserDTO;

@Dao
public interface UserDAO {
    @Insert
    void addUser(UserDTO user);

    @Query("SELECT * FROM users_table WHERE email = :email")
    LiveData<UserDTO> getUserByEmail(String email);

    @Query("SELECT * FROM users_table WHERE email = :email AND password = :password")
    LiveData<UserDTO> getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM users_table")
    LiveData<List<UserDTO>> getAllPeople();

    @Update
    void updateUserInfo(UserDTO user);
}
