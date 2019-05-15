package pbob.kennedy.mymovie.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {

    @SerializedName("results")
    @Expose
    private Movie[] movie;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private String rating;

    @SerializedName("original_language")
    @Expose
    private String language;

    @SerializedName("poster_path")
    @Expose
    private String image;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    private String SORT_BY;

    private String Query;

    public String getSORT_BY() {
        return SORT_BY;
    }

    public void setSORT_BY(String SORT_BY) {
        this.SORT_BY = SORT_BY;
    }

    public Movie() {
    }


    public Movie(int id, String title, String rating, String language, String image, String overview, String release_date) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.language = language;
        this.image = image;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getQuery() {
        return Query;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie[] getMovie() {
        return movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
