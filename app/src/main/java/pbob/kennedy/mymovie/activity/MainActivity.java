package pbob.kennedy.mymovie.activity;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;

import pbob.kennedy.mymovie.R;
import pbob.kennedy.mymovie.adapter.MovieAdapter;
import pbob.kennedy.mymovie.fragment.FavoriteFragment;
import pbob.kennedy.mymovie.fragment.MovieFragment;
import pbob.kennedy.mymovie.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private MovieAdapter movieAdapter = new MovieAdapter();
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item_bnv_now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;
        String sort_by;
        switch (item.getItemId()) {
            case R.id.item_bnv_now_playing:
                sort_by = "now_playing";
                loadFragment(sort_by);
                return true;
            case R.id.item_bnv_upcoming:
                sort_by = "upcoming";
                loadFragment(sort_by);
                return true;
            case R.id.item_bnv_popular:
                sort_by = "popular";
                loadFragment(sort_by);
                return true;
            case R.id.item_bnv_favorite:
                fragment = new FavoriteFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }

    private void loadFragment(String string) {
        Bundle bundle = new Bundle();
        bundle.putString("SORT_BY", string);

        fragment = new MovieFragment();
        fragment.setArguments(bundle);

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
        }

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        MenuInflater menuInflater = getMenuInflater();
////        menuInflater.inflate(R.menu.home_action_bar_item, menu);
////        MenuItem searchItem = menu.findItem(R.id.menu_search);
////        SearchView searchView = (SearchView) searchItem.getActionView();
////        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_language_setting:
//                Intent mintent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                startActivity(mintent);
//                return true;
//            case R.id.menu_search:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
