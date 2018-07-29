package com.example.nihanth_2.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Reviews {
    @SerializedName("results")
    public List<ReviewResult> mResults;
    @SerializedName("page")
    private int mPage;
    @SerializedName("id")
    int id;
    @SerializedName("total_pages")
    int mTotalPages;
    @SerializedName("total_results")
    int mTotalResults;

    Reviews(List<ReviewResult> mResults , int mPage , int id , int mTotalResults , int mTotalPages){
        this.mResults = mResults;
        this.mPage = mPage;
        this.id = id;
        this.mTotalPages = mTotalPages;
        this.mTotalResults = mTotalResults;
    }

    Reviews(List<ReviewResult> mResults){      //new code can
        this.mResults = mResults;
    }

    public List<ReviewResult> getmResults(){return mResults;}
}
