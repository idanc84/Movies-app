package com.exr.moviesapp.data.repository.remote;

import com.exr.moviesapp.data.api.MoviesService;
import com.exr.moviesapp.data.model.MoviesResponse;
import com.exr.moviesapp.data.model.Result;
import com.exr.moviesapp.data.repository.DataConfig;
import com.exr.moviesapp.data.repository.MoviesDataSource;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class MoviesRemoteSource implements MoviesDataSource {

    private MoviesService mMoviesService;

    @Override
    public void addMovies(List<Result> results) {

    }

    @Inject
    public MoviesRemoteSource(MoviesService moviesService){
        mMoviesService = moviesService;
    }

    @Override
    public Flowable<List<Result>> getTopRatedMovies(int page ) {
        return mMoviesService.getTopRatedMovies(DataConfig.API_KEY,DataConfig.EN_LANG, page).map(MoviesResponse::getResults);
    }

    @Override
    public Flowable<MoviesResponse> getTopRatedMoviesResponse(int page) {
        return mMoviesService.getTopRatedMovies(DataConfig.API_KEY,DataConfig.EN_LANG, page);
    }
}
