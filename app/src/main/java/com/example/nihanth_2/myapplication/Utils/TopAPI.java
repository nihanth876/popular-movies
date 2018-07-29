package com.example.nihanth_2.myapplication.Utils;

import com.example.nihanth_2.myapplication.Film;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface TopAPI {
    @GET("movie/top_rated")
    Call<Film> getTopRated(@Query("api_key") String key);
}
