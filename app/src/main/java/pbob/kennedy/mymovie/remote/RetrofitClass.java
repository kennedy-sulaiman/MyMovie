package pbob.kennedy.mymovie.remote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import pbob.kennedy.mymovie.BuildConfig;
import pbob.kennedy.mymovie.adapter.MovieAdapter;
import pbob.kennedy.mymovie.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private final static String BASE_URL = BuildConfig.BASE_URL;
    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String LANGUAGE = "en-US";
    private final static String PAGE = "1";
    private ArrayList<Movie> listMovie;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;

    private static Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIService getAPIService() {
        return getInstance().create(APIService.class);
    }
}