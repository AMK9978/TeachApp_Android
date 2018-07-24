package com.example.launcher.teachapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.launcher.teachapp.Model.to.Teach;

import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TeachActivity extends AppCompatActivity {

    int position;
    TextView context;
    WebView webView;
    Button next_teach_btn,quit_teach;
    Teach teach;
    Teach next_teach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        next_teach_btn = findViewById(R.id.btn_next_teach);
        quit_teach = findViewById(R.id.btn_quit_teach);
        webView = findViewById(R.id.webView);
        context = findViewById(R.id.teach_context);
        new MyTask().execute();
        quit_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeachActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        next_teach_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position!=MainActivity.teachList.getArrayList().size()-1){
                    next_teach = MainActivity.teachList.getArrayList().get(position+1);
                    if (next_teach.getHas_lock() == 1){
                        //go for quiz
                    }else {
                        //go for next lesson with changing fragments
                    }
                }
            }
        });

        Bundle bundle = getIntent().getBundleExtra("bundle");
        position = (int) bundle.get("position");
        teach = MainActivity.teachList.getArrayList().get(position);
        context.setText(teach.getText());

    }

    public void setVideo(){


    }
  class MyTask extends AsyncTask<Void,Void,String>{
      @Override
      protected void onPostExecute(String s) {
          try{
              JSONObject jsonObject = new JSONObject(s);
              JSONObject video = jsonObject.getJSONObject("video");
              String frame = video.getString("frame");
              API.play(webView,frame);
          }catch (Exception ignored){}

      }

      @Override
      protected String doInBackground(Void... params) {
          return API.getData("http://aparat.com/etc/api/video/videohash/UfLu8");
      }
  }

}
