package com.exr.moviesapp.data.repository;

import com.exr.moviesapp.data.model.MoviesResponse;
import com.exr.moviesapp.data.model.Result;

import java.util.List;

import io.reactivex.Flowable;

public interface MoviesDataSource {

    Flowable<List<Result>> getTopRatedMovies(int page);

    Flowable<MoviesResponse> getTopRatedMoviesResponse(int page);

    void addMovies(List<Result> results);
}
