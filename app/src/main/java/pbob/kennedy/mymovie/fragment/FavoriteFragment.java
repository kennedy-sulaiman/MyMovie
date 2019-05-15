package pbob.kennedy.mymovie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pbob.kennedy.mymovie.Database.MovieHelper;
import pbob.kennedy.mymovie.R;
import pbob.kennedy.mymovie.adapter.MovieAdapter;
import pbob.kennedy.mymovie.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private MovieHelper movieHelper;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container,false);
        recyclerView = v.findViewById(R.id.rv_favorite);
        movieHelper = new MovieHelper(getActivity());
        return v ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        showRecycleList();
        loadDatabase();
        super.onActivityCreated(savedInstanceState);
        Log.e("listmovie", String.valueOf(listMovie) );
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDatabase();
    }

    private void loadDatabase() {
        listMovie.clear();
        movieHelper.open();
        listMovie.addAll(movieHelper.getAllData());
        movieHelper.close();
    }

    private void showRecycleList() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        movieAdapter = new MovieAdapter(getActivity(),listMovie);
        movieAdapter.setListMovie(listMovie);
        recyclerView.setAdapter(movieAdapter);
    }
}
