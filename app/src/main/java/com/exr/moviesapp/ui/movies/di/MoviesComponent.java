package com.exr.moviesapp.ui.movies.di;

import com.exr.moviesapp.data.repository.di.RepositoryComponent;
import com.exr.moviesapp.ui.base.ActivityScope;
import com.exr.moviesapp.ui.movies.MoviesFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = {RepositoryComponent.class})
public interface MoviesComponent {
    void inject(MoviesFragment fragment);
}
