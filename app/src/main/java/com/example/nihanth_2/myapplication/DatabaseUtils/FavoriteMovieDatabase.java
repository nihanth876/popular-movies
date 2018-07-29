package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by nihanth_2 on 6/27/2018.
 */

@Database(entities = {FavoriteMovies.class} , version = 1 , exportSchema = false)
public abstract class FavoriteMovieDatabase extends RoomDatabase {
    private static final Object OBJECT = new Object();
    private static final String DATABASE_NAME = "favoritetable";
    private static FavoriteMovieDatabase favoriteDatabase;

    public static FavoriteMovieDatabase getFavoritesDatabase(Context context){
        if(favoriteDatabase == null){
            synchronized (OBJECT){
                Log.d("dbbb","creating");
                favoriteDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteMovieDatabase.class,FavoriteMovieDatabase.DATABASE_NAME)
                        .build();


            }
        }
        Log.d("dbb","returning");
        return favoriteDatabase;
    }
    public abstract FavoriteMovieDao favoriteDao();
}

