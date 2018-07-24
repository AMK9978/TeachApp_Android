package com.example.launcher.teachapp;

import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.launcher.teachapp.Adapter.MyAdapter;
import com.example.launcher.teachapp.Model.TeachList;
import com.example.launcher.teachapp.Model.da.TeachDA;
import com.example.launcher.teachapp.Model.to.Teach;
import com.example.launcher.teachapp.WebService.APIClient;
import com.example.launcher.teachapp.WebService.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static TeachList teachList = new TeachList();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    TeachDA teachDA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        teachDA = new TeachDA(this);
        teachDA.open();

        teachList.setArrayList(teachDA.getAllTeaches());
        if (teachList.getArrayList().size() == 0){
            getTeaches();
            Log.i("payam","need to net");
        }else {
            setScreen(teachList.getArrayList());
        }

    }

    @Override
    protected void onPause() {
        teachDA.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        teachDA.open();
        super.onResume();
    }

    private void getTeaches() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TeachList> call = apiInterface.getTeaches();
        Log.i("payam",Thread.currentThread().getName());
        call.enqueue(new Callback<TeachList>() {
            @Override
            public void onResponse(Call<TeachList> call, Response<TeachList> response) {
                if (response.isSuccessful()){
                    Log.i("payam",Thread.currentThread().getName()+" ,"+ Thread.currentThread().getPriority());
                    teachList = response.body();
                    setScreen(teachList.getArrayList());
                    InsertIntoDB();
                }
            }
            @Override
            public void onFailure(Call<TeachList> call, Throwable t) {
                Log.i("payam",t.getMessage());
            }

        });

    }

    private void setScreen(ArrayList<Teach> arrayList){
        myAdapter = new MyAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void InsertIntoDB(){
        teachDA.addAllTeaches(teachList.getArrayList());
    }

    private void setChanges(){
        myAdapter.notifyDataSetChanged();
    }

}
