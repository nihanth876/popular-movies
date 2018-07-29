package com.example.nihanth_2.myapplication;

import com.google.gson.annotations.SerializedName;


public class ReviewResult {
    @SerializedName("author")
    String mAuthor;
    @SerializedName("content")
    String mContent;
    @SerializedName("id")
    String mId;
    @SerializedName("url")
    String mUrl;

    ReviewResult(String mAuthor , String mContent , String mId , String mUrl){
        this.mAuthor = mAuthor;
        this.mContent = mContent;
        this.mId = mId;
        this.mUrl = mUrl;
    }

    ReviewResult(String mAuthor , String mContent){     //new code can
        this.mContent = mContent;
        this.mAuthor = mAuthor;
    }

    public String getmAuthor(){return mAuthor;}
    public String getmContent(){return mContent;}

}
