package com.example.content_provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.content_provider.Controler.DataBaseHelper;
import com.example.content_provider.Controler.RecAdator;
import com.example.content_provider.Model.Data;
import com.example.content_provider.Views.AddData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static RecAdator myAdapter;
    FloatingActionButton fb;
    DataBaseHelper db;
    List<Data> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        db = new DataBaseHelper(this);

        myAdapter = new RecAdator(db.getAllData(), this, db);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        fb = findViewById(R.id.floatingActionButton4);
        fb.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddData.class);
            startActivity(intent);
        });


    }


    public static void notifyAdapter() {
        myAdapter.notifyDataSetChanged();
    }

//    public List<Data> queryGet() {
//        String uri = "content://com.example.content_provider.Controler/people_db";
//        data = new ArrayList<>();
//
//        Uri students = Uri.parse(uri);
//        Cursor c = getContentResolver().query(students, null, null, null, "name");
//
//        if (c.moveToFirst()) {
//            do {
//                Data date = new Data();
//                date.setId(c.getInt(c.getColumnIndex(Utils.COLUMN_ID)));
//                date.setName(c.getString(c.getColumnIndex(Utils.COLUMN_NAME)));
//                date.setPhone(c.getString(c.getColumnIndex(Utils.COLUMN_PHONE)));
//
//                data.add(date);
//            } while (c.moveToNext());
//        }
//        c.close();
//        return data;
//    }
}