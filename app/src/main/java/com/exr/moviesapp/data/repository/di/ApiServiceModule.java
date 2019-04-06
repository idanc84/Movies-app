package com.exr.moviesapp.data.repository.di;
import com.exr.moviesapp.data.api.MoviesService;
import com.exr.moviesapp.data.repository.DataConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {

    @Provides
    String provideBaseUrl() {
        return DataConfig.BASE_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(provideBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MoviesService provideQuestionService(Retrofit retrofit) {
        return retrofit.create(MoviesService.class);
    }
}
