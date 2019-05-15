package pbob.kennedy.mymovie.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pbob.kennedy.mymovie.Database.DatabaseContract.DATABASE_NAME;
import static pbob.kennedy.mymovie.Database.DatabaseContract.DATABASE_VERSION;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.DESCRIPTION;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.ID;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.IMAGE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.LANGUAGE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.RATING;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.RELEASE_DATE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.MovieColumn.TITLE;
import static pbob.kennedy.mymovie.Database.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    ID + " text not null, " +
                    TITLE + " text not null, " +
                    IMAGE + " text not null, " +
                    RATING + " text not null, " +
                    RELEASE_DATE + " text not null, " +
                    LANGUAGE + " text not null, " +
                    DESCRIPTION + " text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(query);
        onCreate(db);
    }
}
