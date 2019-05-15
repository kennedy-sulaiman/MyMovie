package pbob.kennedy.mymovie.Database;

public class DatabaseContract {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "favoriteDb";
    public static final String TABLE_NAME = "favoriteMovie";

    public static final class MovieColumn {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String IMAGE = "image";
        public static final String RATING = "rating";
        public static final String RELEASE_DATE = "release_date";
        public static final String LANGUAGE = "language";
        public static final String DESCRIPTION = "description";
    }
}
