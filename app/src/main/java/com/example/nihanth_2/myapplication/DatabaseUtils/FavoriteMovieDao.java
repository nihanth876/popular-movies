package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by nihanth_2 on 6/27/2018.
 */

@Dao
public interface FavoriteMovieDao {

    @Query("SELECT * FROM favoritetable ORDER BY movieid")
    LiveData<List<FavoriteMovies>> loadAllFavorites();

    @Insert
    void insertFavorite(FavoriteMovies favorites);

    @Delete
    void deleteFavorite(FavoriteMovies favorites);

    @Query("SELECT * FROM favoritetable WHERE movieid = :mId")
    LiveData<FavoriteMovies> loadFavorite(int mId);

    @Query("DELETE FROM favoritetable WHERE movieid = :mid")
    void deleteById(int mid);
}
