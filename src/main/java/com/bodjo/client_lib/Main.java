//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bodjo.client_lib;

import com.bodjo.client_lib.callbacks.WebSocketReadyCallback;
import com.bodjo.client_lib.http.ApiFactory;
import com.bodjo.client_lib.pojo.GameResponse;
import com.bodjo.client_lib.pojo.LoginResponse;
import com.bodjo.client_lib.ws.WebSocketController;
import com.neovisionaries.ws.client.WebSocketAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main {
    public Main() {
    }

    public static void start(final String username, String password, final boolean ping, final WebSocketAdapter webSocketAdapter, WebSocketReadyCallback webSocketReadyCallback) {
        (new ApiFactory()).login().login(username, password).enqueue(new Callback<LoginResponse>() {
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    if (((LoginResponse) response.body()).getToken() != null) {
                        (new ApiFactory()).play().play(((LoginResponse) response.body()).getToken(), "tanks").enqueue(new Callback<GameResponse>() {
                            public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                                if (response.body() != null) {
                                    if (!((GameResponse) response.body()).getStatus().equals("err")) {
                                        WebSocketController webSocketController = new WebSocketController();
                                        webSocketController.openWebSocketConnection(((GameResponse) response.body()).getPort(), username, ((GameResponse) response.body()).getGameSessionToken(), ping, webSocketAdapter, webSocketReadyCallback);
                                    } else {
                                        System.out.println(((GameResponse) response.body()).toString());
                                    }
                                } else {
                                    System.out.println("Something went wrong, server return empty body, try later");
                                }

                            }

                            public void onFailure(Call<GameResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    } else {
                        System.out.println(((LoginResponse) response.body()).toString());
                    }
                } else {
                    System.out.println("Something went wrong, server return empty body, try later");
                }

            }

            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
