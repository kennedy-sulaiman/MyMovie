package pbob.kennedy.mymovie.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import pbob.kennedy.mymovie.model.Movie;
import pbob.kennedy.mymovie.remote.RetrofitClass;

public class MovieViewModel extends ViewModel {
    private Movie movie;
    private RetrofitClass retrofitClass = new RetrofitClass();

//    public Movie getMovieVal(Context context) {
//        if (movie == null)
//        {
//            movie = retrofitClass.getMovie(context);
//        }
//        return movie;
//    }
}
