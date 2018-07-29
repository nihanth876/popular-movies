package com.example.nihanth_2.myapplication.Utils;

import com.example.nihanth_2.myapplication.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface VideoAPI {
    @GET("movie/{id}/videos")
    Call<Video> getVideos(@Path("id") String id, @Query("api_key") String key);
}
