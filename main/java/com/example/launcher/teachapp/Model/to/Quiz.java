package com.example.launcher.teachapp.Model.to;


public class Quiz {
    private int id;
    private String title;
    private int answer;
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
