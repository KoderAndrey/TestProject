package com.example.testproject.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface RequstDao {
    @Query("SELECT * FROM requstdata")
    Single<List<RequstData>> getAll();

    @Insert
    Completable insert(RequstData requstData);
}
