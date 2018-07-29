package com.example.nihanth_2.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihanth_2.myapplication.Utils.RetrofitUtils;
import com.example.nihanth_2.myapplication.DatabaseUtils.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
// This is the main activity
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    RetrofitUtils retrofitUtils = new RetrofitUtils();
    public List<FilmJsonArray> completeFilms;
    public List<FilmJsonArray> completeFilmsTop;
    public List<String> list = new ArrayList<>();
    public List<String> list_top = new ArrayList<>();
    public static List<String> list_favorite = new ArrayList<>();
    LiveData<List<FavoriteMovies>> favorites;
    List<FavoriteMovies> favoriteList;
    MainViewModelMovie mainViewModel;
    String poster;
    String poster_top;
    FavoriteMovieDatabase favoritesDatabase;
    static int a;
    Parcelable recyclerState;
    private static Bundle bundle;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favoritesDatabase = FavoriteMovieDatabase.getFavoritesDatabase(getApplicationContext());
        mainViewModel = ViewModelProviders.of(this).get(MainViewModelMovie.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if(savedInstanceState != null){
            a = savedInstanceState.getInt("a");

        }
        else{
            a = 0;
        }

        if(isConnected()) {
            popularCollapsingToolbar(a);
        }
        else{
            a = 3;
            Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
            popularCollapsingToolbar(a);
        }
        getFavoriteList();
        if(savedInstanceState != null){
            Log.d("hellooo",""+savedInstanceState.getInt("position"));
            Log.d("success","success");
            recyclerView.scrollToPosition(savedInstanceState.getInt("position"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bundle = new Bundle();
        recyclerState = recyclerView.getLayoutManager().onSaveInstanceState();
        bundle.putParcelable("helllo", recyclerState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isConnected() && a != 3){
            a = -1;
        }
        popularCollapsingToolbar(a);
    }


    private void popularCollapsingToolbar(int az) {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("");
        if (az == 0) {

            retrofitUtils.select(az);
            retrofitUtils.called.enqueue(new Callback<Film>() {
                @Override
                public void onResponse(Call<Film> call, Response<Film> response) {

                    if (response.isSuccessful()) {

                        AppBarLayout appBarLayout = findViewById(R.id.appbar);
                        appBarLayout.setExpanded(true);
                        ImageView imageView = findViewById(R.id.image_collapse);
                        imageView.setImageResource(R.drawable.popular);
                        TextView textView = findViewById(R.id.text_view);
                        textView.setText(R.string.popular);
                        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                            boolean show = false;
                            int range = -1;

                            @Override
                            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                                if (range == -1) {
                                    range = appBarLayout.getTotalScrollRange();
                                }
                                if (range + verticalOffset == 0) {
                                    collapsingToolbarLayout.setTitle("Popular Movies");
                                    show = true;
                                } else if (show) {
                                    collapsingToolbarLayout.setTitle("");
                                    show = false;
                                }
                            }
                        });
                        completeFilms = response.body().getmArray();
                        int x = completeFilms.size();
                        for (int i = 0; i < x; i++) {
                            poster = completeFilms.get(i).getmPoster();
                            if (list.size() < x) {
                                list.add(poster);
                            }
                        }
                        movieAdapter = new MovieAdapter(getApplicationContext(), list);
                        movieAdapter.setClickListener(MainActivity.this);
                        recyclerView.setAdapter(movieAdapter);

                    }


                }

                @Override
                public void onFailure(Call<Film> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }

            });
        } else if (az == 1) {
            retrofitUtils.select(az);
            retrofitUtils.called_top.enqueue(new Callback<Film>() {
                @Override
                public void onResponse(Call<Film> call, Response<Film> response) {
                    if (response.isSuccessful()) {
                        AppBarLayout appBarLayout = findViewById(R.id.appbar);
                        appBarLayout.setExpanded(true);
                        ImageView imageView = findViewById(R.id.image_collapse);
                        imageView.setImageResource(R.drawable.popularity);
                        TextView textView = findViewById(R.id.text_view);
                        textView.setText(R.string.top);
                        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                            boolean show = false;
                            int range = -1;

                            @Override
                            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                                if (range == -1) {
                                    range = appBarLayout.getTotalScrollRange();
                                }
                                if (range + verticalOffset == 0) {
                                    collapsingToolbarLayout.setTitle("Top Rated Movies");
                                    show = true;
                                } else if (show) {
                                    collapsingToolbarLayout.setTitle("");
                                    show = false;
                                }
                            }
                        });
                        completeFilmsTop = response.body().getmArray();
                        int x = completeFilmsTop.size();
                        for (int i = 0; i < x; i++) {
                            poster_top = completeFilmsTop.get(i).getmPoster();
                            if (list_top.size() < x) {
                                list_top.add(poster_top);
                            }

                        }

                        movieAdapter = new MovieAdapter(getApplicationContext(), list_top);
                        movieAdapter.setClickListener(MainActivity.this);
                        recyclerView.setAdapter(movieAdapter);

                    }


                }

                @Override
                public void onFailure(Call<Film> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }

            });


        } else if (az == 3) {
            AppBarLayout appBarLayout = findViewById(R.id.appbar);
            appBarLayout.setExpanded(true);
            ImageView imageView = findViewById(R.id.image_collapse);
            imageView.setImageResource(R.drawable.favorites);
            TextView textView = findViewById(R.id.text_view);
            textView.setText("Favorites");
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean show = false;
                int range = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (range == -1) {
                        range = appBarLayout.getTotalScrollRange();
                    }
                    if (range + verticalOffset == 0) {
                        collapsingToolbarLayout.setTitle("Favorites");
                        show = true;
                    } else if (show) {
                        collapsingToolbarLayout.setTitle("");
                        show = false;
                    }
                }
            });

            movieAdapter = new MovieAdapter(getApplicationContext(), list_favorite);
            movieAdapter.setClickListener(MainActivity.this);
            recyclerView.setAdapter(movieAdapter);

            getFavoriteList();

        }



        else {
            ImageView imageView = findViewById(R.id.image_collapse);
            imageView.setImageResource(R.drawable.error);
            TextView textView = findViewById(R.id.text_view);
            textView.setText("");
            List<String> str = new ArrayList<>();
            movieAdapter = new MovieAdapter(getApplicationContext(), str);
            recyclerView.setAdapter(movieAdapter);
            Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.popularity_menu: {
                if(a == 0){
                    if(completeFilms != null) {
                        Toast.makeText(getApplicationContext(), "sorted by popularity", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else{
                        if(isConnected()) {
                            popularCollapsingToolbar(a);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                            a =  -1;
                            popularCollapsingToolbar(a);
                        }
                    }
                }
                else {
                    a = 0;
                    if(isConnected()) {
                        popularCollapsingToolbar(a);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                        a =  -1;
                        popularCollapsingToolbar(a);
                    }
                }
                break;
            }

            case R.id.top_id:
                if(a == 1){
                    if(completeFilmsTop != null) {
                        Toast.makeText(getApplicationContext(), "sorted by top rating", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else{
                        if(isConnected()) {
                            popularCollapsingToolbar(a);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                            a =  -1;
                            popularCollapsingToolbar(a);
                        }
                    }
                }
                else {
                    a = 1;
                    if(isConnected()) {
                        popularCollapsingToolbar(a);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Unable to load Data.Check Network Connection", Toast.LENGTH_SHORT).show();
                        a =  -1;
                        popularCollapsingToolbar(a);
                    }
                }
                break;

            case R.id.favorite:
                a=3;
                popularCollapsingToolbar(a);
                break;

            default:
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    public void onListItemClick(View view, int pos) {
       if(a==0){

            if(completeFilms.size()>0) {
                getFavoriteList();
                FilmJsonArray filmJsonArray = completeFilms.get(pos);
                String title = filmJsonArray.getTitle();
                Float rating = filmJsonArray.getmVoteAverage();
                String release = filmJsonArray.getmReleaseDate();
                String overview = filmJsonArray.getmOverview();
                String image = filmJsonArray.getmBackDrop();
                String poster = filmJsonArray.getmPoster();
                int id = filmJsonArray.getmId();
                boolean status = false;

                for(int i=0 ; i<list_favorite.size();i++ ){
                    if(poster.equals(list_favorite.get(i))){
                        status = true;
                        break;
                    }

                }

                int z = 0;
                Intent intent = new Intent(this, MovieDetail.class);
                Log.d("hello", title);
                intent.putExtra("title", title);
                intent.putExtra("rating", rating);
                intent.putExtra("overview", overview);
                intent.putExtra("image", image);
                intent.putExtra("release", release);
                intent.putExtra("poster", poster);
                intent.putExtra("id",id);
                intent.putExtra("status", status);
                intent.putExtra("z",z);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Unable to load data Check internet connection",Toast.LENGTH_SHORT).show();
            }
        }
       else if(a == 1){
           if(completeFilmsTop.size()>0) {
               getFavoriteList();
               FilmJsonArray filmJsonArray = completeFilmsTop.get(pos);
               String title = filmJsonArray.getTitle();
               Float rating = filmJsonArray.getmVoteAverage();
               String overview = filmJsonArray.getmOverview();
               String image = filmJsonArray.getmBackDrop();
               String poster = filmJsonArray.getmPoster();
               int id = filmJsonArray.getmId();
               Log.d("mid",""+id);
               boolean status = false;
               for(int i=0 ; i<list_favorite.size();i++ ){
                   if(poster.equals(list_favorite.get(i))){
                       status = true;
                       break;
                   }

               }

               String release = filmJsonArray.getmReleaseDate();
               int z=0;
               Intent intent = new Intent(this, MovieDetail.class);
               intent.putExtra("title", title);
               intent.putExtra("rating", rating);
               intent.putExtra("overview", overview);
               intent.putExtra("image", image);
               intent.putExtra("release", release);
               intent.putExtra("id",id);
               intent.putExtra("poster", poster);
               intent.putExtra("status", status);
               intent.putExtra("z",z);
               startActivity(intent);
           }
           else{
               Toast.makeText(getApplicationContext(),"Unable to load data Check internet connection",Toast.LENGTH_SHORT).show();
           }
       }
       else if(a ==3){
           if(favoriteList.size()>0){
               FavoriteMovies favor = favoriteList.get(pos);
               String key = favor.getKey();
               int id = favor.getmId();

               int z= 1;
               String title = favor.getTitle();
               Float rating = favor.getmVoteAverage();
               String overview = favor.getmOverview();
               String image = favor.getmBackDrop();
               String poster = favor.getmPoster();
               String release = favor.getmReleaseDate();
               String author = favor.getmAuthor();
               String content = favor.getmContent();
               Intent intent = new Intent(this, MovieDetail.class);
               intent.putExtra("title", title);
               intent.putExtra("author",author);
               intent.putExtra("content",content);
               intent.putExtra("key",key);
               intent.putExtra("rating", rating);
               intent.putExtra("overview", overview);
               intent.putExtra("status", true);
               intent.putExtra("image", image);
               intent.putExtra("release", release);
               intent.putExtra("id",id);
               intent.putExtra("poster", poster);
               intent.putExtra("z",z);
               startActivity(intent);
           }
       }
       else{
            Toast.makeText(getApplicationContext(),"Faiiled",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a",a);

    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo != null && networkInfo.isConnectedOrConnecting())){
            return false;
        }
        return true;

    }


    public void getFavoriteList(){
        mainViewModel.getFavorites().observe(this, new Observer<List<FavoriteMovies>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteMovies> favoritess) {
                Log.d("database", "viewmodel update received");
                favoriteList = favoritess;
                list_favorite = new ArrayList<>();
                for (int i = 0; i < favoriteList.size(); i++)

                {
                    String in = favoriteList.get(i).getTitle();
                    int idd = favoriteList.get(i).getmId();
                    Log.d("title", in);
                    Log.d("id", "" + idd);
                    String image = favoriteList.get(i).getmPoster();
                    Log.d("img", image);
                    if (list_favorite.size() < favoriteList.size()) {
                        list_favorite.add(image);

                    }
                }
                if(a == 3) {
                    movieAdapter.setPosters(list_favorite);
                }

            }
        });
    }

    public static List<String> getfavoriteslist() {
        return list_favorite;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        GridLayoutManager gridLayoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
        if(bundle != null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerState = bundle.getParcelable("helllo");
                    recyclerView.getLayoutManager().onRestoreInstanceState(recyclerState);
                }
            },50);
        }

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            if(!isConnected() && a!= 3){
                a = -1;
                popularCollapsingToolbar(a);
            }
            getFavoriteList();
            gridLayoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
            gridLayoutManager.setSpanCount(2);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if(!isConnected() && a!= 3){
                a = -1;
                popularCollapsingToolbar(a);
            }
            getFavoriteList();
            gridLayoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
            gridLayoutManager.setSpanCount(2);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
    }


}





