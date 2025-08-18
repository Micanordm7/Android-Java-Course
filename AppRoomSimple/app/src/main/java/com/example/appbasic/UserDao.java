package com.example.appbasic;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    public List<User> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    public List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    public User findByName(String first, String last);

    @Query("SELECT * FROM users WHERE uid LIKE:userId")
    public User getSingleUser(int userId);

    @Insert
    public void insertAll(User... users);

    @Insert
    public void insertSingleUser(User user);

    @Delete
    public void deleteUser(User user);

    @Update
    public void updateUser(User user);
}

