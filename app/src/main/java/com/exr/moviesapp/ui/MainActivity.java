package com.exr.moviesapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.exr.moviesapp.R;
import com.exr.moviesapp.data.model.Result;
import com.exr.moviesapp.ui.detailedmovie.DetailedMovieActivity;
import com.exr.moviesapp.ui.detailedmovie.DetailedMovieFragment;
import com.exr.moviesapp.ui.movies.MoviesFragment;

public class MainActivity extends AppCompatActivity implements MoviesFragment.InteractionListener {

    private boolean mDualMode;
    private FragmentManager mFm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFm = getSupportFragmentManager();
        mDualMode = isDualMode();

        if( savedInstanceState == null ){
            mFm.beginTransaction().add(R.id.frame_container, MoviesFragment.newInstance(), "moviesFragment").commit();
        }
    }
    
    private boolean isDualMode(){
        FrameLayout fl = (FrameLayout)findViewById(R.id.frame_container_detailed);

        if(fl != null) return true;

        return false;
    }

    @Override
    public void onFragmentInteraction(Result movie) {
        if( !mDualMode ){
            Intent intent = new Intent(this, DetailedMovieActivity.class);
            intent.putExtra("movie",movie);
            startActivity(intent);
        }else {
            mFm.popBackStack();
            Fragment fragment = DetailedMovieFragment.newInstance(movie);
            mFm.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container_detailed, fragment, "movieDetail")
                    .commit();
        }
    }
}
