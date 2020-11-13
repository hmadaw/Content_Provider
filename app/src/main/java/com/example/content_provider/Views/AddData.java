package com.example.content_provider.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.content_provider.Controler.DataBaseHelper;
import com.example.content_provider.MainActivity;
import com.example.content_provider.Model.DataA;
import com.example.content_provider.R;

public class AddData extends AppCompatActivity {
    EditText name, phone;
    Button add;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        db = new DataBaseHelper(this);
        name = findViewById(R.id.name_Add);
        phone = findViewById(R.id.phone_Add);
        add = findViewById(R.id.button_Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = name.getText().toString();
                String phone_text = phone.getText().toString();

                DataA dataA = new DataA(name_text, phone_text);
                long id = db.addData(dataA);
                Intent intent = new Intent(AddData.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}