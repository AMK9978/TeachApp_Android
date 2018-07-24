package com.example.launcher.teachapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.launcher.teachapp.MainActivity;
import com.example.launcher.teachapp.Model.to.Teach;
import com.example.launcher.teachapp.R;
import com.example.launcher.teachapp.TeachActivity;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Teach> arrayList = new ArrayList<>();
    private Context context;

    public MyAdapter(ArrayList<Teach> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myviewholderlayout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//        holder.imageView.setImageDrawable(android.R.drawable.ic_menu_view);
        holder.textView.setText(arrayList.get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_teach_name);
            imageView = itemView.findViewById(R.id.seen_symbol);
        }
    }
}
