package com.example.launcher.teachapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.launcher.teachapp.Model.da.TeachDA;
import com.example.launcher.teachapp.Model.to.Teach;
import org.json.JSONObject;

public class TeachActivity extends AppCompatActivity {

    static int position;
    TextView context;
    WebView webView;
    Button next_teach_btn,quit_teach;
    static Teach teach;
    Teach next_teach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        next_teach_btn = findViewById(R.id.btn_next_teach);
        quit_teach = findViewById(R.id.btn_quit_teach);
        webView = findViewById(R.id.webView);
        context = findViewById(R.id.teach_context);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        position = (int) bundle.get("position");
        teach = (Teach) bundle.getSerializable("object");
        next_teach_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != HomeFragment.teachList.getArrayList().size()-1){
                    next_teach = HomeFragment.teachList.getArrayList().get(position+1);
                    if (next_teach.getHas_lock() == 1){
                        //go for quiz
                        Intent intent = new Intent(TeachActivity.this,QuizActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("teach",teach);
                        intent.putExtra("bundle_for_quiz",bundle);
                        startActivity(intent);

                    }else {
                        //go for next lesson with changing items
                        teach = next_teach;
                        position = position+1;
                        onStart();
                    }
                }else{
                    Intent intent = new Intent(TeachActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        quit_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeachActivity.this,MainActivity.class);
                Log.i("payam",teach.getText());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("payam",teach.getText()+","+teach.getVideo_url());
        context.setText(teach.getText());
        teach.setSeen(1);
        teach.setHas_lock(0);
        TeachDA teachDA = new TeachDA(this);
        teachDA.open();
        teachDA.updateTeach(teach);
        teachDA.close();
        new MyTask().execute();

    }

  private class MyTask extends AsyncTask<Void,Void,String>{
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
          return API.getData("http://aparat.com/etc/api/video/videohash/84VA9");
      }
  }

}
