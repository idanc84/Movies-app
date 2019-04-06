package com.exr.moviesapp.ui.detailedmovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exr.moviesapp.R;
import com.exr.moviesapp.data.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailedMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedMovieFragment extends Fragment {

    @BindView(R.id.movie_backdrop)
    ImageView mImageBackdrop;
    @BindView(R.id.movie_title)
    TextView mTitle;
    @BindView(R.id.movie_overview)
    TextView mDesc;
    @BindView(R.id.movie_release_date)
    TextView mReleaseDate;
    @BindView(R.id.movie_language)
    TextView mLanguage;
    @BindView(R.id.movie_rating)
    RatingBar mRatingBar;

    private Result mRes;

    public DetailedMovieFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailedMovieFragment.
     */
    public static DetailedMovieFragment newInstance(Result result) {
        DetailedMovieFragment fragment = new DetailedMovieFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie",result);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if ( args != null) {
            mRes = args.getParcelable("movie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        mTitle.setText(mRes.getTitle());
        mDesc.setText(mRes.getOverview());
        mReleaseDate.setText(mRes.getReleaseDate());
        mLanguage.setText(mRes.getOriginalLanguage());
        mRatingBar.setRating((float) (mRes.getVoteAverage()/2));

        Glide.with(this)
                .load(BASE_URL_BACKDROP + mRes.getBackdropPath())
                .into(mImageBackdrop);

        return view;
    }

    private static final String BASE_URL_BACKDROP = "http://image.tmdb.org/t/p/w780/";
}
