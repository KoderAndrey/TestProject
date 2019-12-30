package com.example.testproject.di.module;

import android.content.Context;

import androidx.room.Room;

import com.example.testproject.AppContext;
import com.example.testproject.data.local.RequestDatabase;
import com.example.testproject.data.local.RequstDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private static final String DATABASE = "database";

    @Singleton
    @Provides
    RequstDao provideRequestDatabase(@AppContext Context context) {
        return Room.databaseBuilder(context,
                RequestDatabase.class, DATABASE).build().requstDao();
    }
}
