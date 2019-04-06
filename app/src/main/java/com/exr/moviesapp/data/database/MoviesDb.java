package com.exr.moviesapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.exr.moviesapp.data.model.Result;

@Database(entities = Result.class, version = 1)
@TypeConverters(Convertor.class)
public abstract class MoviesDb extends RoomDatabase{
    public abstract MoviesDao getMoviesDao();

}
