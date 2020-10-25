package com.example.content_provider.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.content_provider.Model.Data;
import com.example.content_provider.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, Utils.DATA_BASE_NAME, null, Utils.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Utils.TABLE_NAME  +" ("
                + Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME + " TEXT,"
                + Utils.COLUMN_PHONE + " TEXT"
                + ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        onCreate(db);
    }

    public long addData (Data data){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.COLUMN_NAME , data.getName());
        cv.put(Utils.COLUMN_PHONE, data.getPhone());
        long id =database.insert(Utils.TABLE_NAME , null ,cv);
        database.close();
        return (int) id ;
    }

    public int updateData (Data data){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.COLUMN_NAME , data.getName());
        cv.put(Utils.COLUMN_PHONE, data.getPhone());
        return database.update(Utils.TABLE_NAME , cv , "id" + " =?"
                , new String[]{String.valueOf(data.getId())});
    }

    public int deleteData(Data data){
        SQLiteDatabase database = getWritableDatabase();
        int id = database.delete(Utils.TABLE_NAME , Utils.COLUMN_ID + " =?"
                        , new String[]{String.valueOf(data.getId())});
        database.close();
        return id ;
    }

    public Data getData(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{Utils.COLUMN_ID,Utils.COLUMN_NAME,Utils.COLUMN_PHONE}
                ,Utils.COLUMN_ID+" =?"
                ,new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        Data data = new Data(
                cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONE))
        );
        cursor.close();
        return data ;

    }
    public List<Data> getAllData() {
        List<Data> allData = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ Utils.TABLE_NAME ;

        Cursor cursor = db.rawQuery( query ,null);
        if(cursor.moveToFirst())
            do{
                Data data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(Utils.COLUMN_ID)));
                data.setName( cursor.getString(cursor.getColumnIndex(Utils.COLUMN_NAME)));
                data.setPhone( cursor.getString(cursor.getColumnIndex(Utils.COLUMN_PHONE)));
                allData.add(data);

            }while (cursor.moveToNext());
        db.close();
        cursor.close();
        return allData;
    }
}
