package com.example.mygeoapp;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;


public class ContentProvider extends android.content.ContentProvider {

    private UriMatcher uriMatcher;
    private final  static String AUTHORITY = "com.example.mygeoapp";
    private final static String PATH = "user";
    private UserDao userDao;
    private Database db;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH, 1);
        uriMatcher.addURI(AUTHORITY, PATH + "/#", 2);

        db= Room.databaseBuilder(getContext(),Database.class, "users").allowMainThreadQueries().build();
        userDao= db.userDao();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        MatrixCursor cursor2 = new MatrixCursor(new String[]{"id", "latitude", "longitude"});
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                cursor= userDao.getCursorAll();
                break;
            case 2:
                int id= Integer.parseInt(uri.getLastPathSegment());
                User user= userDao.getUserById(id);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
