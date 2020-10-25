package com.example.content_provider.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.content_provider.Controler.DataBaseHelper;
import com.example.content_provider.Controler.DataProvider;
import com.example.content_provider.MainActivity;
import com.example.content_provider.Model.Data;
import com.example.content_provider.R;

public class EditData extends AppCompatActivity {
    EditText name , phone ;
    Button edit ;
    DataBaseHelper db ;
    Data personInfo ;
    int position ;
    String str_position ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Bundle bundle = getIntent().getExtras();
        str_position =bundle.getString("position");
        position =Integer.valueOf(str_position);
        db =new DataBaseHelper(this);
        personInfo = db.getData(position);

        name = findViewById(R.id.name_Edit);
        phone = findViewById(R.id.phone_Edit);
        edit = findViewById(R.id.button_Edit);

        name.setText(personInfo.getName());
        phone.setText(personInfo.getPhone());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfo.setName(name.getText().toString());
                personInfo.setPhone(phone.getText().toString());
                db.updateData(personInfo);
                MainActivity.notifyAdapter();
                Intent intent = new Intent(EditData.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}