package com.example.launcher.teachapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

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


    RecyclerView favorite_recycler,notify_recycler;
    MyAdapter myAdapter;
    TeachDA teachDA;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigationView);

        favorite_recycler = findViewById(R.id.favorite_recycler);
        notify_recycler = findViewById(R.id.notify_recycler);
        Menu menu = bottomNavigationView.getMenu();
        selectFragment(menu.getItem(2));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return false;
            }
        });
    }

    public void selectFragment(MenuItem menuItem){
        menuItem.setChecked(true);
        int id = menuItem.getItemId();
        switch (id){
            case R.id.notifies:
                setFragment(new NotifiesFragment());
                break;
            case R.id.favorites:
                setFragment(new FavoriteFragment());
                break;
            case R.id.home:
                setFragment(new HomeFragment());
                break;
        }

    }

    public void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }


}
