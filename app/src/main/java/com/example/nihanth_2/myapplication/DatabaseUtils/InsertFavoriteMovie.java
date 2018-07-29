package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


public class InsertFavoriteMovie extends ViewModel {

    private LiveData<FavoriteMovies> favoriteLiveData;

    public InsertFavoriteMovie(FavoriteMovieDatabase favoriteDatabase,int id){
        favoriteLiveData = favoriteDatabase.favoriteDao().loadFavorite(id);
    }

    public LiveData<FavoriteMovies> getFavoriteLiveData(){
        return favoriteLiveData;
    }
}
