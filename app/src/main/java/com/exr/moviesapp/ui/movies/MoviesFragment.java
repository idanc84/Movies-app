package com.exr.moviesapp.ui.movies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.exr.moviesapp.R;
import com.exr.moviesapp.data.model.Result;
import com.exr.moviesapp.root.MoviesApplication;
import com.exr.moviesapp.ui.base.BaseViewModel;
import com.exr.moviesapp.ui.movies.di.DaggerMoviesComponent;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment implements MoviesContract.MoviesView {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject MoviesPresenter presenter;
    private MoviesAdapter mMoviesAdapter;
    private InteractionListener mListener;

    public MoviesFragment() {
        // Required empty public constructor
    }

    public interface InteractionListener {
        void onFragmentInteraction(Result movie);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MoviesFragment.
     */
    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if( context instanceof InteractionListener ){
            mListener = (InteractionListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        initPresenter();

        if (savedInstanceState != null) {
            presenter.restoreCurrentPage(savedInstanceState.getInt("page"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("page", presenter.getPageNumber());
        super.onSaveInstanceState(outState);
    }

    private void initPresenter(){
        BaseViewModel viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        presenter = (MoviesPresenter)viewModel.getPresenter();

        if( presenter == null ){
            DaggerMoviesComponent.builder()
                    .repositoryComponent(((MoviesApplication)getActivity().getApplication()).getRepositoryComponent())
                    .build()
                    .inject(this);

            viewModel.setPresenter(presenter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        initList();

        return view;
    }

    private void initList(){
        Context ctx = getContext();

        int gridRowNum = getResources().getInteger(R.integer.grid_column_num);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx,gridRowNum);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMoviesAdapter = new MoviesAdapter(ctx, presenter);
        mRecyclerView.setAdapter(mMoviesAdapter);

        /*mRecyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {


        });*/
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttach(this);
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    private boolean mDualPan;


    @Override
    public void showMovies( ) {
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void openMovie(Result result) {
        mListener.onFragmentInteraction(result);
    }
}
