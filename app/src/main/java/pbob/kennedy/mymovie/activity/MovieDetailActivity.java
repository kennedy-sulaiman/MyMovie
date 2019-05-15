package pbob.kennedy.mymovie.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import pbob.kennedy.mymovie.Database.MovieHelper;
import pbob.kennedy.mymovie.R;
import pbob.kennedy.mymovie.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView ivPoster;
    private int id;
    private String image;
    private String title;
    private String rating;
    private String language;
    private String releasee_date;
    private String desc;

    private TextView tvReleaseDate;
    private TextView tvOverview;
    private TextView tvLang;
    private TextView tvRating;
    private TextView tvTitle;
    private boolean favorite;
    private final static String URL_POSTER = "https://image.tmdb.org/t/p/w185";

    private Movie movie;
    private MovieHelper movieHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_item);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }

        id = getIntent().getIntExtra("ID", 0);
        image = getIntent().getStringExtra("POSTER");
        title = getIntent().getStringExtra("TITLE");
        movieHelper = new MovieHelper(this);
        rating = getIntent().getStringExtra("VOTE_AVERAGE");
        desc = getIntent().getStringExtra("OVERVIEW");
        releasee_date = getIntent().getStringExtra("RELEASE_DATE");
        language = getIntent().getStringExtra("LANGUAGE");

        ivPoster = findViewById(R.id.ci_detail_poster);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvLang = findViewById(R.id.tv_detail_language);
        tvRating = findViewById(R.id.tv_detail_rating);
        tvOverview = findViewById(R.id.tv_detail_overview);
        tvReleaseDate = findViewById(R.id.tv_detail_release_date);

        Glide.with(this)
                .load(URL_POSTER + image)
                .into(ivPoster);
        tvTitle.setText(title);
        tvRating.setText(rating);
        tvOverview.setText(desc);
        tvReleaseDate.setText(releasee_date);
        tvLang.setText(language);
        this.setTitle(R.string.detail_film);

    }

    public int getId() {
        return id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        movie = new Movie();
        movieHelper.open();
        switch (item.getItemId()) {
            case R.id.menu_favorite:

                if (!movieHelper.exist(String.valueOf(id))) {
                    addToFavorite();
                    item.setIcon(R.drawable.ic_star_favorite_black_24dp);
                    return true;
                }
                removeFromFavorite();
                item.setIcon(R.drawable.ic_star_unfavorite_black_24dp);

                invalidateOptionsMenu();
                return true;
        }
        movieHelper.close();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        movie = new Movie();
        movieHelper.open();
        if (movieHelper.exist(String.valueOf(id))) {
            menu.findItem(R.id.menu_favorite)
                    .setTitle(R.string.favorited)
                    .setIcon(R.drawable.ic_star_favorite_black_24dp);
            return true;
        }
        menu.findItem(R.id.menu_favorite)
                .setTitle(R.string.unfavorited)
                .setIcon(R.drawable.ic_star_unfavorite_black_24dp);
        movieHelper.close();
        return super.onPrepareOptionsMenu(menu);
    }

    private void addToFavorite() {
        movie = new Movie(id,title,rating,language,image,desc,releasee_date);
        movieHelper.open();

        long result = movieHelper.insert(movie);
        if (result > 0) {
            Toast.makeText(this, getResources().getText(R.string.favorited), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failed to add", Toast.LENGTH_SHORT).show();
        }

        movieHelper.close();
    }

    private void removeFromFavorite() {
        movie = new Movie();
        movieHelper.open();

        int result = movieHelper.delete(String.valueOf(id));
        if (result > 0) {
            Toast.makeText(this, getResources().getText(R.string.unfavorited), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failed to delete", Toast.LENGTH_SHORT).show();
        }
        movieHelper.close();
    }

}
