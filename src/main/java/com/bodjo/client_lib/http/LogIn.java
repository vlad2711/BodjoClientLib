package com.bodjo.client_lib.http;

import com.bodjo.client_lib.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LogIn {

    @POST("/login")
    public Call<LoginResponse> login(@Query("username") String username,
                                     @Query("password") String password);
}
