package com.example.mygeoapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "users")
public class User {

    @PrimaryKey(autoGenerate = false)
    public int id;

    @ColumnInfo(name="latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;

}
