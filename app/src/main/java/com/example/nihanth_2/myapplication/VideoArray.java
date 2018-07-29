package com.example.nihanth_2.myapplication;

import com.google.gson.annotations.SerializedName;


public class VideoArray {
    @SerializedName("id") String id;
    @SerializedName("iso_639_1") String iso;
    @SerializedName("iso_3166_1") String isomod;
    @SerializedName("key") String key;
    @SerializedName("name") String name;
    @SerializedName("site") String site;
    @SerializedName("size") String size;
    @SerializedName("type") String type;

    VideoArray(String id , String iso , String isomod , String key , String name , String site
    , String size , String type){
        this.id = id;
        this.iso = iso;
        this.isomod = isomod;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    VideoArray(String key){     //new code can
        this.key = key;
    }

    public String getKey(){return key;}
    public String getType(){return type;}


}
