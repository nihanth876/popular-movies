package com.example.nihanth_2.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nihanth_2.myapplication.Utils.RetrofitUtils;

import java.util.List;


public class ReviewDetail extends AppCompatActivity {
    int id;
    ReviewAdapter reviewAdapter;
    RecyclerView recyclerView;
    RetrofitUtils retrofitUtils;
    List<ReviewResult> reviewResults;
    String author , content;
    List<String> list_author , list_content;
    String m_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_content);
        recyclerView = findViewById(R.id.recycle_reviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(getApplicationContext(),"Faiiled",Toast.LENGTH_SHORT).show();
        }


        if (intent != null) {
            id = intent.getIntExtra("id",0);
            list_author = intent.getStringArrayListExtra("author");
            list_content = intent.getStringArrayListExtra("content");
            Log.d("id",""+id);
        }
        Log.d("m_id",""+id);
        m_id = ""+id;
        reviewAdapter = new ReviewAdapter(getApplicationContext(), list_author, list_content);
        recyclerView.setAdapter(reviewAdapter);

    }
}
