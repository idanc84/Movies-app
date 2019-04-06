package com.exr.moviesapp.ui.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exr.moviesapp.R;
import com.exr.moviesapp.data.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter {

    private Context mCtx;
    private MoviesPresenter mPresenter;

    public MoviesAdapter(Context ctx, MoviesPresenter presenter){
        super();
        mPresenter = presenter;
        mCtx = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from( viewGroup.getContext() );
        View row = inflater.inflate( R.layout.movie_image_place, viewGroup,false );

        return new MovieViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final @NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MovieViewHolder holder = (MovieViewHolder)viewHolder;

        mPresenter.bindArticleRowView(i, holder);

        if ((i >= getItemCount() - 1)) {
            mPresenter.loadMovies();
        }
    }

    @Override
    public int getItemCount() {
        return mPresenter.getItemsSize();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements MoviesContract.MoviePlaceItem{

        @BindView(R.id.movie_place)
        ImageView imageView;

        public MovieViewHolder( View view ) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onItemClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void bind(Result result, int i) {
            Glide.with(mCtx).load(BASE_URL + result.getPosterPath()).into(imageView);
        }
    }

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w185/";
}
