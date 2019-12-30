package com.example.testproject.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RequstData.class}, version = 1)
public abstract class RequestDatabase extends RoomDatabase {
    public abstract RequstDao requstDao();
}
