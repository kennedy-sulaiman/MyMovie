package pbob.kennedy.mymovie.remote;

import pbob.kennedy.mymovie.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
//    https://api.themoviedb.org/3/movie/upcoming?api_key=51149b87f806e1f0f9dd56f82c1759fb&language=en-US&page=1
    @GET("movie/{sort_by}")
    Call<Movie> listMovie (
            @Path("sort_by") String args,
            @Query("api_key") String apiKey,
            @Query("language") String lang,
            @Query("page") String page);

    //https://api.themoviedb.org/3/search/movie?api_key=51149b87f806e1f0f9dd56f82c1759fb&language=en-US&query=avenger&page=1
    @GET("search/movie")
    Call<Movie> searchedList (
            @Query("api_key") String apikey,
            @Query("language") String lang,
            @Query("query") String query,
            @Query("page") String page);

}
