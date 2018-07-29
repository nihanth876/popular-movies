package com.example.nihanth_2.myapplication.Utils;

import com.example.nihanth_2.myapplication.Film;
import com.example.nihanth_2.myapplication.Reviews;
import com.example.nihanth_2.myapplication.Video;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitUtils {

    String api_key = "";     // enter the api key here for movie db

    static final String BASE_URL = "http://api.themoviedb.org/3/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PopularAPI popularAPI = retrofit.create(PopularAPI.class);
    TopAPI topAPI = retrofit.create(TopAPI.class);
    VideoAPI videoAPI = retrofit.create(VideoAPI.class);
    ReviewsAPI reviewsAPI = retrofit.create(ReviewsAPI.class);
    public Call<Film> called;
    public Call<Film> called_top;
    public Call<Video> called_video;
    public Call<Reviews> called_reviews;
    public void select(int z){
        if(z == 0){
            called = popularAPI.getPopular(api_key);
        }
        else if (z == 1){
            called_top = topAPI.getTopRated(api_key);
        }


    }

    public void videos(String id){
        called_video = videoAPI.getVideos(id,api_key);
    }

    public void reviews(String id){
        called_reviews = reviewsAPI.getReviews(id,api_key);

    }


}
