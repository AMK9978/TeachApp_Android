package com.example.launcher.teachapp.Model.da;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.launcher.teachapp.MyDataBaseHelper;

public class QuizDa {
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public QuizDa(Context context){
        sqLiteOpenHelper = new MyDataBaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteDatabase.close();
    }


}
