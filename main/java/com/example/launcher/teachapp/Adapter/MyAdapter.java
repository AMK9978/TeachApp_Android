package com.example.launcher.teachapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.launcher.teachapp.Model.da.TeachDA;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.textView.setText(arrayList.get(position).getName());
        if (arrayList.get(position).getHas_lock() == 1){
            holder.lock_symbol.setImageResource(android.R.drawable.ic_secure);
        }else{
            holder.lock_symbol.setImageDrawable(null);
        }
        if (arrayList.get(position).isSeen() == 1){
            holder.seen_symbol.setImageResource(android.R.drawable.ic_menu_view);
        }else{
            holder.seen_symbol.setImageDrawable(null);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.get(position).getHas_lock() == 1){
                    return;
                }
                TeachDA teachDA = new TeachDA(context);
                teachDA.open();

                arrayList.get(position).setSeen(1);
                teachDA.updateTeach(arrayList.get(position));
                setDBChanges(arrayList);
                Intent intent = new Intent(context, TeachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putSerializable("object",arrayList.get(position));
                intent.putExtra("bundle",bundle);
                teachDA.close();
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
        ImageView seen_symbol,lock_symbol;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_teach_name);
            seen_symbol = itemView.findViewById(R.id.seen_symbol);
            lock_symbol = itemView.findViewById(R.id.lock_symbol);
        }
    }

    private void setDBChanges(ArrayList<Teach> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();

    }
}
