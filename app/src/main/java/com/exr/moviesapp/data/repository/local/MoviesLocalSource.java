package com.exr.moviesapp.data.repository.local;

import com.exr.moviesapp.data.database.MoviesDao;
import com.exr.moviesapp.data.model.MoviesResponse;
import com.exr.moviesapp.data.model.Result;
import com.exr.moviesapp.data.repository.MoviesDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MoviesLocalSource implements MoviesDataSource {

    private MoviesDao mMoviesDao;

    @Inject
    public MoviesLocalSource(MoviesDao moviesDao){
        mMoviesDao = moviesDao;
    }

    @Override
    public void addMovies(List<Result> results) {
        mMoviesDao.insertMovieResults(results);
    }

    @Override
    public Flowable<List<Result>> getTopRatedMovies(int page) {
        return mMoviesDao.getMoviesResults(page);
    }

    @Override
    public Flowable<MoviesResponse> getTopRatedMoviesResponse(int page) {
        throw new UnsupportedOperationException("Not supported for local source");
    }
}
