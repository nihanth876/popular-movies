package com.example.nihanth_2.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHoldReview> {

    Context context;
    List<String> author;
    List<String> content;

    public class ViewHoldReview extends RecyclerView.ViewHolder{

        public TextView textView;
        public TextView textView1;
        public ViewHoldReview(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.content);
            textView1 = itemView.findViewById(R.id.author);
        }



    }

    @NonNull
    @Override
    public ViewHoldReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHoldReview(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldReview holder, int position) {
        String x = author.get(position);
        String y = content.get(position);
        holder.textView.setText(y);
        holder.textView1.setText(x);
    }

    @Override
    public int getItemCount() {
        return author.size();
    }




    ReviewAdapter(Context context, List<String> author , List<String> content) {
        this.context = context;
        this.author = author;
        this.content = content;
    }


}


