package com.bodjo.client_lib.http;

import com.bodjo.client_lib.pojo.GameResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Play {
    @GET("/play")
    public Call<GameResponse> play(@Query("token") String token,
                                   @Query("gameName") String gameName);
}
