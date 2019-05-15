package pbob.kennedy.mymovie.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pbob.kennedy.mymovie.model.Movie;

import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.DESCRIPTION;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.ID;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.IMAGE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.LANGUAGE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.RATING;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.RELEASE_DATE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.TITLE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.TABLE_NAME;

public class MovieHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private final static String URL_POSTER = "https://image.tmdb.org/t/p/w185";

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) databaseHelper.close();
    }

    public ArrayList<Movie> getAllData() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Movie> listMovie = new ArrayList<>();
        Movie movieModel;

        if (cursor.getCount() > 0) {
            do {
                movieModel = new Movie();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movieModel.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                movieModel.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                movieModel.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                movieModel.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                listMovie.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return listMovie;
    }

    public boolean exist(String id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " =?";
        Cursor cursor = database.rawQuery(query,new String[] {id});
        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }
        cursor.close();
        database.close();
        return hasObject;
    }

    public long insert(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, movie.getId());
        contentValues.put(TITLE, movie.getTitle());
        contentValues.put(IMAGE, movie.getImage());
        contentValues.put(RATING,movie.getRating());
        contentValues.put(DESCRIPTION,movie.getOverview());
        contentValues.put(RELEASE_DATE,movie.getRelease_date());
        contentValues.put(LANGUAGE,movie.getLanguage());
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public int delete(String id) {
        return database.delete(TABLE_NAME, ID + "= '" + id + "'", null);
    }
}
