package com.example.nihanth_2.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class FilmJsonArray {
    @SerializedName("poster_path") public String mPoster;
    @SerializedName("title") String title;
    @SerializedName("vote_count") int mVoteCount;
    @SerializedName("id") int mId;
    @SerializedName("video") boolean mVideo;
    @SerializedName("vote_average") float mVoteAverage;
    @SerializedName("popularity") float mPopularity;
    @SerializedName("original_language") String mOriginal;
    @SerializedName("original_title") String mOriginalTitle;
    @SerializedName("genre_ids")
    List<Integer> mGenre;
    @SerializedName("backdrop_path") String mBackDrop;
    @SerializedName("adult") String mAdult;
    @SerializedName("overview") String mOverview;
    @SerializedName("release_date") String mReleaseDate;

    FilmJsonArray(String mPoster,String title,int mVoteCount,int mId,boolean mVideo,float mVoteAverage,float mPopularity,
    String mOriginal,String mOriginalTitle,List<Integer> mGenre,String mBackDrop,String mAdult,String mOverview,String mReleaseDate ){
        this.mAdult = mAdult;
        this.mBackDrop = mBackDrop;
        this.mGenre = mGenre;
        this.mId = mId;
        this.mOriginal = mOriginal;
        this.mOriginalTitle = mOriginalTitle;
        this.mPopularity = mPopularity;
        this.mPoster = mPoster;
        this.mReleaseDate = mReleaseDate;
        this.mVideo = mVideo;
        this.mVoteCount = mVoteCount;
        this.mOverview = mOverview;
        this.mVoteAverage = mVoteAverage;
        this.title = title;
    }

    public FilmJsonArray(String mPoster , String title , float mVoteAverage , String mBackDrop ,
                  String mReleaseDate , int mId , String mOverview){  //new code can be removed
        this.mId = mId;
        this.mPoster = mPoster;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.title = title;
        this.mBackDrop = mBackDrop;
        this.mOverview = mOverview;
    }

    public void setmPoster(String mPoster){
        this.mPoster = mPoster;
    }
    public String getmPoster(){
        return mPoster;
    }
    public String getTitle(){
        return title;
    }
    public String getmOverview(){
        return mOverview;
    }
    public float getmVoteAverage(){
        return mVoteAverage;
    }
    public String getmBackDrop(){
        return mBackDrop;
    }
    public String getmReleaseDate(){return mReleaseDate;}
    public int getmId(){return mId;}
}
