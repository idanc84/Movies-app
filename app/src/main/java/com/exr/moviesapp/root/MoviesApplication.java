package com.exr.moviesapp.root;

import android.app.Application;

import com.exr.moviesapp.data.repository.di.DaggerRepositoryComponent;
import com.exr.moviesapp.data.repository.di.RepositoryComponent;
import com.squareup.leakcanary.LeakCanary;

public class MoviesApplication extends Application {

    private RepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        repositoryComponent = DaggerRepositoryComponent.builder()
                .application(this)
                .build();

        /*if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }*/
    }

    public RepositoryComponent getRepositoryComponent(){

        return repositoryComponent;
    }
}
