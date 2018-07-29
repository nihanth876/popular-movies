package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class InsertViewModelFactoryMovie extends ViewModelProvider.NewInstanceFactory {

    private final FavoriteMovieDatabase favoriteDatabase;
    private final int id;

    public InsertViewModelFactoryMovie(FavoriteMovieDatabase favoriteDatabase , int id){
        this.favoriteDatabase = favoriteDatabase;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new InsertFavoriteMovie(favoriteDatabase,id);
    }
}
