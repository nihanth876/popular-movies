package com.example.nihanth_2.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHold> {

    Context context;
    List<String> list;
    OnListItemClickListener onListItemClickListener;

    public interface OnListItemClickListener {
        void onListItemClick(View view, int pos);
    }

    public class ViewHold extends RecyclerView.ViewHolder {

        public TextView textView;


        public ViewHold(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.video_text);

        }

    }

        VideoAdapter(Context context , List<String> list){
        this.context = context;
        this.list = list;
        }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false);
        return new VideoAdapter.ViewHold(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        int hi = position+1;
        String hello = "Trailer "+hi;
        final int num = holder.getAdapterPosition();
        holder.textView.setText(hello);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClickListener.onListItemClick(v,num);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void onSetClickListener(OnListItemClickListener onItemClickListener){
        onListItemClickListener = onItemClickListener;
    }
}


