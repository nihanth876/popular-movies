package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by nihanth_2 on 6/23/2018.
 */

public class MainViewModelMovie extends AndroidViewModel {

    LiveData<List<FavoriteMovies>> favorites;
    FavoriteMovieDatabase favoriteDatabase;



    public MainViewModelMovie(@NonNull Application application) {
        super(application);
        favoriteDatabase = FavoriteMovieDatabase.getFavoritesDatabase(this.getApplication());
        favorites = favoriteDatabase.favoriteDao().loadAllFavorites();

    }

    public LiveData<List<FavoriteMovies>> getFavorites(){
        return favorites;
    }

    public void addFavorite(FavoriteMovies favoriteMovies){
        favoriteDatabase.favoriteDao().insertFavorite(favoriteMovies);
    }

    public void deleteFavorite(int id){
        favoriteDatabase.favoriteDao().deleteById(id);
    }
}
