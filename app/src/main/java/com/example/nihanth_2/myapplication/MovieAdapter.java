package com.example.nihanth_2.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;




public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHold> {

    Context context;
    List<String> movie;
    ListItemClickListener onItemClickListener;


    public interface ListItemClickListener {
        void onListItemClick(View view, int pos);
    }


    public class ViewHold extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public ViewHold(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);



        }



    }

    MovieAdapter(Context context, List<String> movie) {
        this.context = context;
        this.movie = movie;


    }

    @NonNull
    @Override
    public MovieAdapter.ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new ViewHold(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.ViewHold holder, int position) {
        String hello = movie.get(position);
        String path = "http://image.tmdb.org/t/p/w185/".concat(hello);
        final int num = holder.getAdapterPosition();
        Picasso.with(context).load(path).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  onItemClickListener.onListItemClick(v , num);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public void setClickListener(ListItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setPosters(List<String> poster) {
        movie = poster;
        notifyDataSetChanged();

    }

}

