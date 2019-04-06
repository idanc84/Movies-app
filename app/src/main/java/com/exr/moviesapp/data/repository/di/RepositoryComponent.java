package com.exr.moviesapp.data.repository.di;

import android.content.Context;

import com.exr.moviesapp.data.repository.Repository;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApiServiceModule.class, RepositoryModule.class, DatabaseModule.class})
public interface RepositoryComponent {
    Repository provideRepository();

    @Component.Builder
    interface Builder {

        RepositoryComponent build();

        @BindsInstance
        Builder application(Context application);
    }
}
