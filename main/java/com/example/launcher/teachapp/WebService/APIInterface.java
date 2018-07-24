package com.example.launcher.teachapp.WebService;

import com.example.launcher.teachapp.Model.TeachList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("teach")
    Call<TeachList> getTeaches();

    


}
