package com.exr.moviesapp.ui.base;

public interface BasePresenter<V> {
    void onAttach(V view);
    void onDetach();
}