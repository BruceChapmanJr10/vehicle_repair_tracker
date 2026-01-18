package com.example.vehiclerepairtracker.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vehiclerepairtracker.entities.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM Users WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);


}
