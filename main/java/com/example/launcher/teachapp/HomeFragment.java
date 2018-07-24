package com.example.launcher.teachapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class HomeFragment extends Fragment {

    RecyclerView home_recycler;
    View view;
    MyAdapter myAdapter;
    TeachDA teachDA;
    public static TeachList teachList = new TeachList();

    @Override
    public void onStart() {
        super.onStart();
        Log.i("payam","onStart()");
        teachDA = new TeachDA(getContext());
        teachDA.open();
        teachList.setArrayList(teachDA.getAllTeaches());
        if (teachList.getArrayList().size() == 0){
            getTeaches();
            Log.i("payam","need to net");
        }else {
            setScreen(teachList.getArrayList());
        }
        teachDA.close();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("payam","onCreateView()");
        view =  inflater.inflate(R.layout.fragment_home,container,false);
        home_recycler = view.findViewById(R.id.home_recycler);
        return view;
    }

    @Override
    public void onPause() {
        teachDA.close();
        super.onPause();
    }

    @Override
    public void onResume() {
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
                    for (int i = 0; i < teachList.getArrayList().size(); i++) {
                        Log.i("payam",teachList.getArrayList().get(i).getName()+","
                                +teachList.getArrayList().get(i).getHas_lock()+","+teachList.getArrayList().get(i).getText());
                    }

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
        if (arrayList == null){
            Log.i("payam","arrayList Null ast");

        }
        if (home_recycler == null){
            Log.i("payam","home_recycler Null ast");
        }

        Log.i("payam", String.valueOf(arrayList.size()));
        if (getContext() == null)
        {
            Log.i("payam","getContext() Null ast");
        }
        myAdapter = new MyAdapter(arrayList,getContext());
        home_recycler.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        home_recycler.setLayoutManager(linearLayoutManager);
    }

    private void InsertIntoDB(){
        teachDA.addAllTeaches(teachList.getArrayList());
    }

}
