package pbob.kennedy.mymovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pbob.kennedy.mymovie.R;
import pbob.kennedy.mymovie.activity.MovieDetailActivity;
import pbob.kennedy.mymovie.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;

    private ArrayList<Movie> listMovie;
    private ArrayList<Movie> listMovieFull;

    private final static String URL_POSTER = "https://image.tmdb.org/t/p/w185";

    public MovieAdapter() {
    }



    public MovieAdapter(Context context, ArrayList<Movie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
        listMovieFull = new ArrayList<>(listMovie);
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(getListMovie().get(position).getTitle());
        Glide.with(context)
                .load(URL_POSTER + getListMovie().get(position).getImage())
                .into(holder.ivPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("ID", getListMovie().get(position).getId());
                intent.putExtra("POSTER", getListMovie().get(position).getImage());
                intent.putExtra("TITLE", getListMovie().get(position).getTitle());
                intent.putExtra("VOTE_AVERAGE", getListMovie().get(position).getRating());
                intent.putExtra("LANGUAGE", getListMovie().get(position).getLanguage());
                intent.putExtra("OVERVIEW", getListMovie().get(position).getOverview());
                intent.putExtra("RELEASE_DATE", getListMovie().get(position).getRelease_date());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public void setFilter(ArrayList<Movie> movies){
        listMovie = new ArrayList<>();
        listMovie.addAll(movies);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_item_list_poster);
            tvTitle = itemView.findViewById(R.id.tv_item_list_title);
        }
    }
}
