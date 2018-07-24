package com.example.launcher.teachapp.Model.da;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.launcher.teachapp.Model.to.Teach;
import com.example.launcher.teachapp.MyDataBaseHelper;

import java.util.ArrayList;

public class TeachDA {
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public TeachDA(Context context){
        sqLiteOpenHelper = new MyDataBaseHelper(context);
    }

    public void addTeach(Teach teach){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseHelper.TEACH_NAME, teach.getName());
        contentValues.put(MyDataBaseHelper.TEACH_HAS_LOCK, teach.getHas_lock());
        contentValues.put(MyDataBaseHelper.TEACH_TEXT, teach.getText());
        contentValues.put(MyDataBaseHelper.TEACH_VIDEO_URL, teach.getVideo_url());
        sqLiteDatabase.insert(MyDataBaseHelper.TABLE_TEACHES,null,contentValues);
    }

    public void addAllTeaches(ArrayList<Teach> arrayList){

        for (int i = 0; i < arrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDataBaseHelper.TEACH_NAME, arrayList.get(i).getName());
            contentValues.put(MyDataBaseHelper.TEACH_HAS_LOCK, arrayList.get(i).getHas_lock());
            contentValues.put(MyDataBaseHelper.TEACH_TEXT, arrayList.get(i).getText());
            contentValues.put(MyDataBaseHelper.TEACH_VIDEO_URL, arrayList.get(i).getVideo_url());
            sqLiteDatabase.insert(MyDataBaseHelper.TABLE_TEACHES,null,contentValues);
        }

    }

    public ArrayList<Teach> getAllTeaches(){
        ArrayList<Teach> arrayList = new ArrayList<>();
        String[] columns = new String[]{MyDataBaseHelper.TEACH_ID,MyDataBaseHelper.TEACH_NAME,
                MyDataBaseHelper.TEACH_HAS_LOCK, MyDataBaseHelper.TEACH_VIDEO_URL};
        Cursor cursor = sqLiteDatabase.query(MyDataBaseHelper.TABLE_TEACHES,columns,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Teach teach = new Teach();
                teach.setId(cursor.getInt(0));
                teach.setName(cursor.getString(1));
                teach.setHas_lock(cursor.getInt(2));
                teach.setVideo_url(cursor.getString(3));
                teach.setText(cursor.getString(4));
                arrayList.add(teach);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteDatabase.close();
    }



}
