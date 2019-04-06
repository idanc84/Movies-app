package com.exr.moviesapp.ui.movies;

import com.exr.moviesapp.data.model.Result;

public interface MoviesContract {

    interface MoviesView{
        void showMovies();

        void showError( String error );

        void openMovie( Result result );
    }

    interface MoviesPresenter{

        void loadMovies();
    }

    interface MoviePlaceItem{
        void bind(Result result, int i);
    }

}
