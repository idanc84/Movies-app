package com.exr.moviesapp.ui.detailedmovie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.exr.moviesapp.R;
import com.exr.moviesapp.data.model.Result;

public class DetailedMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);
        setActionBar();

        if( savedInstanceState == null ) {
            Result result = getIntent().getParcelableExtra("movie");
            if(result != null){
                getSupportFragmentManager().beginTransaction().add(R.id.frame_container, DetailedMovieFragment.newInstance(result)).commit();
            }
        }
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
