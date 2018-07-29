package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "favoritetable")
public class FavoriteMovies {
    @PrimaryKey(autoGenerate = true)
    int id;
    String mPoster ; String title ; float mVoteAverage ; String mBackDrop ;

    String mReleaseDate ;
    int movieid;
    String mOverview;
    String key;
    String mAuthor ; String mContent;

    @Ignore
    public FavoriteMovies(String mPoster , String title , float mVoteAverage , String mBackDrop ,
                    String mReleaseDate , int movieid , String mOverview ,String key,String mAuthor , String mContent){
        this.movieid = movieid;
        this.mPoster = mPoster;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.title = title;
        this.mBackDrop = mBackDrop;
        this.mOverview = mOverview;
        this.key = key;
        this.mContent = mContent;
        this.mAuthor = mAuthor;
    }

    FavoriteMovies(int id , String mPoster , String title , float mVoteAverage , String mBackDrop ,
             String mReleaseDate , int movieid , String mOverview,String key,String mAuthor , String mContent){
        this.id = id;
        this.movieid = movieid;
        this.mPoster = mPoster;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.title = title;
        this.mBackDrop = mBackDrop;
        this.mOverview = mOverview;
        this.key = key;
        this.mContent = mContent;
        this.mAuthor = mAuthor;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}



    public void setmPoster(String mPoster){
        this.mPoster = mPoster;
    }
    public String getmPoster(){
        return mPoster;
    }

    public void setTitle(String title){this.title = title;}
    public String getTitle(){
        return title;
    }

    public void setmOverview(String mOverview){this.mOverview = mOverview;}
    public String getmOverview(){
        return mOverview;
    }

    public void setmVoteAverage(Float mVoteAverage){this.mVoteAverage = mVoteAverage;}
    public float getmVoteAverage(){
        return mVoteAverage;
    }

    public void setmBackDrop(String mBackDrop){this.mBackDrop = mBackDrop;}
    public String getmBackDrop(){
        return mBackDrop;
    }

    public void setmReleaseDate(String mReleaseDate){this.mReleaseDate = mReleaseDate;}
    public String getmReleaseDate(){return mReleaseDate;}

    public void setmId(int movieid){this.movieid = movieid;}
    public int getmId(){return movieid;}


    public String getmAuthor(){return mAuthor;}
    public void setmAuthor(String mAuthor){this.mAuthor = mAuthor;}

    public String getmContent(){return mContent;}
    public void setmContent(String mContent){this.mContent = mContent;}


    public String getKey(){return key;}
    public void setKey(String key){this.key = key;}

}

