package com.exr.moviesapp.data.repository.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.exr.moviesapp.data.database.MoviesDao;
import com.exr.moviesapp.data.database.MoviesDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MoviesDb provideStackOverflowDao(Context context) {
        return Room.databaseBuilder(context, MoviesDb.class, "movies").build();
    }

    @Provides
    @Singleton
    MoviesDao provideArticleDao(MoviesDb db){
        return db.getMoviesDao();
    }
}
