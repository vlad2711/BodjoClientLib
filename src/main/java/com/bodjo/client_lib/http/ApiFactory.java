package com.bodjo.client_lib.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private OkHttpClient CLIENT = new OkHttpClient();
    private Retrofit retrofit;

    public ApiFactory(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://vkram.shpp.me:3518")
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }

    public LogIn login(){
        return retrofit.create(LogIn.class);
    }

    public Play play(){
        return retrofit.create(Play.class);
    }
}
