package com.exr.moviesapp.ui.movies;

import com.exr.moviesapp.data.model.Result;
import com.exr.moviesapp.data.repository.Repository;
import com.exr.moviesapp.ui.base.BasePresenter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter implements BasePresenter<MoviesContract.MoviesView>, MoviesContract.MoviesPresenter {

    private CompositeDisposable mDisposables;
    private MoviesContract.MoviesView mView;
    private Repository mRepository;
    private int mPage = 1;

    List<Result> mResults;

    @Inject
    public MoviesPresenter(Repository repository){
        mRepository = repository;
        mResults = new ArrayList<>();
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onAttach(MoviesContract.MoviesView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mDisposables.clear();
        mView = null;
    }

    private Flowable<List<Result>> mRequest;

    private Flowable<List<Result>> createRequest(){
        return mRepository.getTopRatedMovies(mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void loadMovies() {

        if( mRequest == null ){
            mRequest = createRequest();
        }

        Disposable disposable = mRequest.subscribe(this::handleMoviesResult, this::handleMoviesError, () -> {});

        mDisposables.add(disposable);

        mPage++;
    }

    public void start(){
        if( mResults != null && !mResults.isEmpty() && mRequest == null ){
            mView.showMovies();
        }
        else {
            loadMovies();
        }
    }

    private void handleMoviesResult(List<Result> results){
        if( results != null && !results.isEmpty() ) {
            mResults.addAll(results);
            mView.showMovies();
        }

        mRequest = null;
        mDisposables.clear();
    }

    private void handleMoviesError(Throwable t){
        mView.showError("Failed to load movies");
    }

    public void onItemClick( int i ) {
        Result result = mResults.get(i);

        mView.openMovie( result );
    }

    public int getItemsSize(){
        return (mResults!=null)? mResults.size() : 0 ;
    }

    public void bindArticleRowView( int i, MoviesContract.MoviePlaceItem view ) {
        Result result = mResults.get(i);

        view.bind(result, i);
    }

    public int getPageNumber(){
        return mPage;
    }

    public void restoreCurrentPage( int pageNumber ){
        mPage = pageNumber;
    }

    /*public void clean(){
        mDisposables.dispose();
    }*/
}
