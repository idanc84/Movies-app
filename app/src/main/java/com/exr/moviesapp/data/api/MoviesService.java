package com.exr.moviesapp.data.api;

import com.exr.moviesapp.data.model.MoviesResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {
    @GET("top_rated")
    Flowable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);
}
