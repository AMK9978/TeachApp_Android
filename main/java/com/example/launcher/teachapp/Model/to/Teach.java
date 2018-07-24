package com.example.launcher.teachapp.Model.to;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Teach implements Serializable{

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("hasLock")
    private int has_lock;
    @SerializedName("video_url")
    private String video_url;
    @SerializedName("text")
    private String text;
    private int seen = 0;

    public int isSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHas_lock() {
        return has_lock;
    }

    public void setHas_lock(int has_lock) {
        this.has_lock = has_lock;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
