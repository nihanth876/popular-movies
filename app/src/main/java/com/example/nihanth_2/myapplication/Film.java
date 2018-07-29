package com.example.nihanth_2.myapplication;


import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Film {

    @SerializedName("results")
    private List<FilmJsonArray> mArray;
    @SerializedName("page")
            private int mPage;
    @SerializedName("total_results")
            private int mTotalResults;
    @SerializedName("total_pages")
            private int mTotlaPages;

    Film(List<FilmJsonArray> mArray,int mPage,int mTotalResults,int mTotlaPages){
        this.mArray = mArray;
        this.mPage = mPage;
        this.mTotalResults = mTotalResults;
        this.mTotlaPages = mTotlaPages;


    }

    public Film(List<FilmJsonArray> mArray){       //new code can be removed
        this.mArray = mArray;
    }
    List<FilmJsonArray> getmArray(){
        return mArray;
    }


}