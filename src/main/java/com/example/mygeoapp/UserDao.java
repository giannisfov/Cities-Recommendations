package com.example.mygeoapp;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    public List<User> getAll();

    @Query("SELECT * FROM users")
    public Cursor getCursorAll();

    @Query("SELECT * FROM users  WHERE id LIKE :userId")
    public User getUserById(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User ... users);

    @Query("DELETE FROM users WHERE latitude = :myLatitude AND longitude=:myLongitude")
    abstract void deleteUser(double myLatitude, double myLongitude);

}
