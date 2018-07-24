package com.example.launcher.teachapp.Model;


import com.example.launcher.teachapp.Model.to.Teach;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeachList{

    @SerializedName("teaches")
    private ArrayList<Teach>arrayList;


    public ArrayList<Teach> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Teach> arrayList) {
        this.arrayList = arrayList;
    }
}
