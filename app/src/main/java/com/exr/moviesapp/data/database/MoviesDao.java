package com.exr.moviesapp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.exr.moviesapp.data.model.Result;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface MoviesDao {

    @Query("SELECT * FROM movies WHERE pageNum == :page ")
    Flowable<List<Result>> getMoviesResults( int page );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieResults(List<Result> results );

    @Query("DELETE FROM movies")
    void clearTable();
}
