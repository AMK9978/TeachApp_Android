package com.example.launcher.teachapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_TEACHES = "teaches";
    public static final String TEACH_ID = "id";
    public static final String TEACH_NAME = "name";
    public static final String TEACH_HAS_LOCK = "has_lock";
    public static final String TEACH_VIDEO_URL = "video_url";
    public static final String TEACH_TEXT = "text";

    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZ_ID = "id";
    public static final String QUIZ_TITLE = "title";
    public static final String QUIZ_ANSWER = "answer";
    public static final String QUIZ_TEACH_ID = "teach_id";

    public MyDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXIST '"+TABLE_TEACHES+"' ("+TEACH_ID+" INTEGER PRIMARY KEY" +
                "AUTOINCREMENT ,"+TEACH_NAME+" TEXT ,"+TEACH_HAS_LOCK+" TINYINT(1) DEFAULT 1,"+
                TEACH_VIDEO_URL+" TEXT ,"+TEACH_TEXT+" TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXIST '"+TABLE_QUIZZES+"' ("+QUIZ_ID+" INTEGER PRIMARY KEY" +
                "AUTOINCREMENT ,"+QUIZ_TITLE+" TEXT ,"+QUIZ_ANSWER+" TINYINT(1) DEFAULT 1,"+
                QUIZ_TEACH_ID+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_TEACHES+"");
        db.execSQL("DROP TABLE IF EXIST "+TABLE_QUIZZES+"");
        onCreate(db);
    }
}
