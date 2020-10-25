package com.example.content_provider.Controler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.content_provider.MainActivity;
import com.example.content_provider.Model.Data;
import com.example.content_provider.R;
import com.example.content_provider.Views.EditData;

import java.util.List;

public class RecAdator extends RecyclerView.Adapter<RecAdator.MyViewHolder> {
    List<Data> modelList;
    Context context;
    DataBaseHelper dataBaseHelper;


    public RecAdator(List<Data> modelList, Context context, DataBaseHelper dataBaseHelper) {
        this.modelList = modelList;
        this.context = context;
        this.dataBaseHelper = dataBaseHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_ruw,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data model=modelList.get(position);
        holder.id.setText(String.valueOf(model.getId()));
        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.edit.setOnClickListener(v -> {
            Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , EditData.class);
            intent.putExtra("position" , String.valueOf(model.getId()));
            context.startActivity(intent);
        });
        holder.delete.setOnClickListener(v -> deleteData(position));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , id ,phone;
        ImageButton delete ,edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            id = itemView.findViewById(R.id.text_id);
            phone = itemView.findViewById(R.id.text_phone);
            delete = itemView.findViewById(R.id.deleteButton);
            edit = itemView.findViewById(R.id.edit_Button);
        }
    }

    private  void deleteData(int position){
        dataBaseHelper.deleteData(modelList.get(position));
        modelList.remove(position);
        MainActivity.notifyAdapter();
    }
}
