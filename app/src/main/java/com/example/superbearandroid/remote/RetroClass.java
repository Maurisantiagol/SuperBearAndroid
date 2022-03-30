package com.example.superbearandroid.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {


    static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();


    private static final String BASE_URL=URLS.BASE_URL;

    private static Retrofit getRetroInstance()
    {

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static APIService getAPIService()
    {
        return getRetroInstance().create(APIService.class);
    }



}
