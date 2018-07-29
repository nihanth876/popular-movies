package com.example.nihanth_2.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihanth_2.myapplication.DatabaseUtils.*;
import com.example.nihanth_2.myapplication.Utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetail extends AppCompatActivity implements VideoAdapter.OnListItemClickListener {
    String title;
    Float rating;
    String overview;
    String release;
    String image;
    static String BASE_URL = "http://image.tmdb.org/t/p/w185/";
    String path,poster_path;
    TextView textView;
    String poster;
    CheckBox checkBox;
    int id;
    RecyclerView recyclerView;
    List<String> hello = new ArrayList<>();
    RetrofitUtils retrofitUtils = new RetrofitUtils();
    VideoAdapter videoAdapter;
    List<VideoArray> videos;
    List<String> get_id;
    Button b;
    List<ReviewResult> reviewResults;
    String author , content , key;
    List<String> list_author , list_content;
    int z;
    String id_mod;
    String get;
    FavoriteMovieDatabase favoritesDatabase;
    boolean status;
    MainViewModelMovie mainViewModel;
    static List<String> favoriteMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModelMovie.class);
        ImageView image_view = findViewById(R.id.image_iv);
        ImageView imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView4);
        get_id = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list_author = new ArrayList<>();
        list_content = new ArrayList<>();
        b = findViewById(R.id.button_review);
        favoritesDatabase = FavoriteMovieDatabase.getFavoritesDatabase(getApplicationContext());



        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(getApplicationContext(),"Faiiled",Toast.LENGTH_SHORT).show();
        }


        if (intent != null) {
            z = intent.getIntExtra("z",-1);
            if(z == 0) {
                title = intent.getStringExtra("title");
                rating = intent.getFloatExtra("rating", 0.0f);
                overview = intent.getStringExtra("overview");
                image = intent.getStringExtra("image");
                release = intent.getStringExtra("release");
                poster = intent.getStringExtra("poster");
                id = intent.getIntExtra("id", 0);
                status = intent.getBooleanExtra("status",false);
                Log.d("stat",""+status);
            }
            else if(z == 1){
                title = intent.getStringExtra("title");
                rating = intent.getFloatExtra("rating", 0.0f);
                overview = intent.getStringExtra("overview");
                image = intent.getStringExtra("image");
                release = intent.getStringExtra("release");
                poster = intent.getStringExtra("poster");
                id = intent.getIntExtra("id", 0);
                author = intent.getStringExtra("author");
                content = intent.getStringExtra("content");
                key = intent.getStringExtra("key");
                status = intent.getBooleanExtra("status",false);
            }
            else{
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        }
        if(image != null) {
            path = BASE_URL.concat(image);
            Picasso.with(this)
                    .load(path)
                    .error(R.drawable.error)
                    .into(image_view);
        }
        else{
            Picasso.with(this)
                   .load(R.drawable.error)
                   .into(image_view);
        }
        poster_path = BASE_URL.concat(poster);
        Picasso.with(this)
               .load(poster_path)
               .error(R.drawable.error)
               .into(imageView);
        id_mod =""+id;
        Log.d("idd",id_mod);
        if(z == 0) {
            retrofitUtils.videos(id_mod);
            retrofitUtils.called_video.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Call<Video> call, Response<Video> response) {
                    videos = response.body().getVideoArrays();
                    int x = videos.size();
                    for (int i = 0; i < x; i++) {
                        get = videos.get(i).getKey();
                        Log.d("get", get);
                        if (get != null) {
                            get_id.add(i, get);
                        } else {
                            get_id.add("x");
                        }

                    }
                    videoAdapter = new VideoAdapter(getApplicationContext(), get_id);
                    videoAdapter.onSetClickListener(MovieDetail.this);
                    recyclerView.setAdapter(videoAdapter);
                }

                @Override
                public void onFailure(Call<Video> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            retrofitUtils.reviews(id_mod);
            retrofitUtils.called_reviews.enqueue(new Callback<Reviews>() {
                @Override
                public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                    if (response.isSuccessful()) {
                        reviewResults = response.body().getmResults();
                        int x = reviewResults.size();
                        if (x > 0) {
                            for (int i = 0; i < x; i++) {
                                author = reviewResults.get(i).getmAuthor();
                                content = reviewResults.get(i).getmContent();
                                list_author.add(author);
                                list_content.add(content);
                            }
                        } else {
                            author = "not available";
                            content = "not available";
                            list_author.add(author);
                            list_content.add(content);
                        }

                    }
                }

                @Override
                public void onFailure(Call<Reviews> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        if(z == 1){
            list_author.add(author);
            list_content.add(content);
            get_id.add(key);
            videoAdapter = new VideoAdapter(getApplicationContext(), get_id);
            videoAdapter.onSetClickListener(MovieDetail.this);
            recyclerView.setAdapter(videoAdapter);
        }

        populateUI();




    }

    private void populateUI() {
        TextView title_view = findViewById(R.id.title_name);
        checkBox = findViewById(R.id.radioButton2);
        TextView rating_view = findViewById(R.id.rating);

        title_view.setText(title);
        final String rating_s;
        if(rating != 0.0f) {
            rating_s = "" + rating;
        }
        else{
            rating_s = "Rating not available";
        }
        rating_view.setText(rating_s);
        TextView overview_view = findViewById(R.id.overview);
        overview_view.setText(overview);
        TextView release_view = findViewById(R.id.release_value);
        release_view.setText(release);
        textView.setText(title);

        checkBox.setChecked(status);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MovieDetail.this,ReviewDetail.class);
                i.putStringArrayListExtra("author",(ArrayList<String>)list_author);
                i.putStringArrayListExtra("content",(ArrayList<String>)list_content);
                startActivity(i);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("idddd",""+id);
                favoriteMovies = MainActivity.getfavoriteslist();
                final FavoriteMovies favorites = new FavoriteMovies(poster,title,rating,image,
                        release, id , overview ,get,author ,content);
                if((checkBox.isChecked())){
                    AppExecutorMovie.getAppExecutor().getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("iddddd",""+favorites.getmId());
                            mainViewModel.addFavorite(favorites);
                            favoriteMovies.add(poster);
                            Log.d("db","success");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"added to favorites",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });


                }
                else if(!(checkBox.isChecked())){
                    AppExecutorMovie.getAppExecutor().getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mainViewModel.deleteFavorite(id);
                            favoriteMovies.remove(poster);
                            Log.d("delb","success");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"removed from favorites",Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    });


                }
            }
        });


    }


    @Override
    public void onListItemClick(View view, int pos) {
        String partial_path = get_id.get(pos);
        Log.d("path",partial_path);
        String path = "http://www.youtube.com/watch?v=".concat(partial_path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(path));
        startActivity(intent);
    }

    //public static List<String> sendList(){
    //  return favoriteMovies;
    //}

}
