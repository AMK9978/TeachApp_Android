package com.example.launcher.teachapp.Model.to;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quiz implements Serializable{
    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String title;
    @SerializedName("answer")
    private int answer;
    @SerializedName("teach_id")
    private int teach_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getTeach_id() {
        return teach_id;
    }

    public void setTeach_id(int teach_id) {
        this.teach_id = teach_id;
    }
}
