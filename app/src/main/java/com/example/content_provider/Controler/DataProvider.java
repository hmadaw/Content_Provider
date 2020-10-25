package com.example.content_provider.Controler;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.content_provider.Model.Data;
import com.example.content_provider.Utils.Utils;

import java.util.HashMap;

public class DataProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.content_provider.Controler.DataProvider";

    static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private SQLiteDatabase db;
    DataBaseHelper dbHelper ;
    Data data ;

    @Override
    public boolean onCreate() {
        uriMatcher.addURI(PROVIDER_NAME ,"student", Utils.STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME ,"student/#", Utils.STUDENT_ID);

        return false;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        dbHelper = new DataBaseHelper(getContext());
        db = dbHelper.getReadableDatabase();
        int result = uriMatcher.match(uri);
        if(result == Utils.STUDENTS) {
            return db.query(Utils.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }else if (result == Utils.STUDENT_ID){
            String id = uri.getPathSegments().get(1);
            Cursor c = db.query(Utils.TABLE_NAME, projection, "id=?",new String[]{id}
            , null, null, sortOrder);
            return c;
        }
        return null;
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

