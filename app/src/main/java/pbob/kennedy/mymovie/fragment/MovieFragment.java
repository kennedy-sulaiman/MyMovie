package pbob.kennedy.mymovie.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;

import pbob.kennedy.mymovie.BuildConfig;
import pbob.kennedy.mymovie.R;
import pbob.kennedy.mymovie.activity.MainActivity;
import pbob.kennedy.mymovie.adapter.MovieAdapter;
import pbob.kennedy.mymovie.model.Movie;
import pbob.kennedy.mymovie.remote.APIService;
import pbob.kennedy.mymovie.remote.RetrofitClass;
import pbob.kennedy.mymovie.viewmodel.MovieViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements SearchView.OnQueryTextListener{

    private String SORT_BY;
    private String QUERY;
    private RecyclerView recyclerView;
    private ArrayList<Movie> listMovie;
    private MovieAdapter movieAdapter = new MovieAdapter();
    private ProgressBar progressBar;
    private MainActivity mainActivity = new MainActivity();

    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String LANGUAGE = "en-US";
    private final static String PAGE = "1";

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_action_bar_item, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SORT_BY = getArguments().getString("SORT_BY");
        QUERY = getArguments().getString("QUERY");
        Movie movie = new Movie();
        movie.setSORT_BY(SORT_BY);
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = v.findViewById(R.id.rv_fragment);
        progressBar = v.findViewById(R.id.pb_fragment);
        Log.e("sortby", SORT_BY);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showRecycleView();
    }

    private void showRecycleView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadJson("");
    }

    private void loadJson(String s) {
        APIService apiService = RetrofitClass.getAPIService();
        if (s.isEmpty()) {
            apiService.listMovie(SORT_BY, API_KEY, LANGUAGE, PAGE).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movies = response.body();
                    listMovie = new ArrayList<>(Arrays.asList(movies.getMovie()));
                    movieAdapter = new MovieAdapter(getActivity(), listMovie);
                    recyclerView.setAdapter(movieAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.d("Error : ", t.getMessage());
                }
            });
        } else {
            apiService.searchedList(API_KEY, LANGUAGE, s, PAGE).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    listMovie = new ArrayList<>(Arrays.asList(movie.getMovie()));
                    movieAdapter = new MovieAdapter(getActivity(), listMovie);
                    recyclerView.setAdapter(movieAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        loadJson(s);
        return true;
    }
}
