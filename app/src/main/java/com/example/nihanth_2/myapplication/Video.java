package com.example.nihanth_2.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Video {
    @SerializedName("id") int id;
    @SerializedName("results")
    List<VideoArray> videoArrays;

    Video(int id , List<VideoArray> videoArrays){
        this.id = id;
        this.videoArrays = videoArrays;
    }

    Video(List<VideoArray> videoArrays){        //new code can
        this.videoArrays = videoArrays;
    }

    public List<VideoArray> getVideoArrays(){return videoArrays;}
}
