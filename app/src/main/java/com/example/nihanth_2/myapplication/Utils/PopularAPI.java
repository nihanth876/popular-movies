package com.example.nihanth_2.myapplication.Utils;

import com.example.nihanth_2.myapplication.Film;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface PopularAPI {

    @GET("movie/popular") Call<Film> getPopular(@Query("api_key") String key);

}


