package com.exr.moviesapp.data.repository;

import android.widget.LinearLayout;

import com.exr.moviesapp.data.model.MoviesResponse;
import com.exr.moviesapp.data.model.Result;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class Repository implements MoviesDataSource {

    private MoviesDataSource mRemoteDataSource;
    private MoviesDataSource mLocalDataSource;

    @Inject
    public Repository(@Named("remote") MoviesDataSource remoteDataSource, @Named("local") MoviesDataSource localDataSource ){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public Flowable<MoviesResponse> getTopRatedMoviesResponse(int page) {
        return mRemoteDataSource.getTopRatedMoviesResponse(page);
    }

    @Override
    public void addMovies(List<Result> results) {

    }

    @Override
    public Flowable<List<Result>> getTopRatedMovies(int page){
        return Flowable.concat( mLocalDataSource.getTopRatedMovies(page), getResultsFromRemote(page))
                .firstElement()
                .toFlowable();
    }

    private Flowable<List<Result>> getResultsFromRemote(int page){
        return mRemoteDataSource.getTopRatedMovies(page).map(results -> {
            for (Result res: results) {
                res.setPage(page);
            }

            mLocalDataSource.addMovies(results);
            return results;
        });
    }
}
