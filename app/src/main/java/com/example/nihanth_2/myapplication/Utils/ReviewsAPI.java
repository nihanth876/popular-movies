package com.example.nihanth_2.myapplication.Utils;

import com.example.nihanth_2.myapplication.Reviews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ReviewsAPI {
    @GET("movie/{id}/reviews")
    Call<Reviews> getReviews(@Path("id") String id, @Query("api_key") String key);
}
