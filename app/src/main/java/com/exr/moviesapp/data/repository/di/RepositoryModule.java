package com.exr.moviesapp.data.repository.di;
import com.exr.moviesapp.data.repository.MoviesDataSource;
import com.exr.moviesapp.data.repository.local.MoviesLocalSource;
import com.exr.moviesapp.data.repository.remote.MoviesRemoteSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Named("remote")
    @Singleton
    public MoviesDataSource provideRemoteSource( MoviesRemoteSource moviesRemoteSource ) {
        return moviesRemoteSource;
    };

    @Provides
    @Named("local")
    @Singleton
    public MoviesDataSource provideLocalSource( MoviesLocalSource moviesLocalSource ) {
        return moviesLocalSource;
    };

    /*@Singleton
    //@Named("remote")
    @Binds
    public abstract MoviesDataSource provideRemoteSource(MoviesRemoteSource moviesRemoteSource ) ;*/

    /*@Singleton
    @Named("local")
    @Binds
    public  abstract ArticleDataSource provideLocalSource( ArticlesLocalSource articlesRemoteSource );*/

    /*@Provides
    @Singleton
    @Named("local")
    public ArticleDataSource provideLocalSource( ArticlesLocalSource articlesRemoteSource ) {
        return articlesRemoteSource;
    }*/
}
