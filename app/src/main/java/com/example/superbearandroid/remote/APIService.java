package com.example.superbearandroid.remote;


import com.example.superbearandroid.models.ReadGroups;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {



    @POST("app/grupslist")
    Call<ReadGroups> getGroup(@Body JsonObject jsonObject);


}
