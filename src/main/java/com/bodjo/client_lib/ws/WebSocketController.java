package com.bodjo.client_lib.ws;

import com.bodjo.client_lib.callbacks.WebSocketReadyCallback;
import com.bodjo.client_lib.pojo.ConnectToServerModel;
import com.google.gson.Gson;
import com.neovisionaries.ws.client.*;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WebSocketController {
    private WebSocket ws;

    public void openWebSocketConnection(int port, String username, String gameSessionToken, boolean ping, WebSocketAdapter webSocketAdapter, WebSocketReadyCallback webSocketReadyCallback){

        try {
            WebSocketFactory factory = new WebSocketFactory();
            SSLContext context = NaiveSSLContext.getInstance("TLS");

            factory.setSSLContext(context);
            factory.setVerifyHostname(false);

            System.out.println(port);
            ws = factory.createSocket("wss://vkram.shpp.me:" + port + "/", 5000);
            ws.addHeader("Accept-Encoding", "gzip, deflate, br");
            ws.addHeader("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6");
            ws.addHeader("Cache-Control", "no-cache");
            ws.addHeader("Connection", "Upgrade");
            ws.addHeader("Host", "vkram.shpp.me:" + port);
            ws.addHeader("Origin", "https://bodjo.net");

            if(ping) {
                Timer t = new Timer();
                t.scheduleAtFixedRate(new TimerTask() {

                    @Override
                    public void run() {
                        ws.sendText("ping");
                    }

                }, 0, 3000);
            }

            ws.addListener(webSocketAdapter);

            try {
                ws.connect();
            } catch (WebSocketException e) {
                e.printStackTrace();
            }

            ws.sendText(new Gson().toJson(new ConnectToServerModel("connect", username, gameSessionToken, "player")));

            System.out.println(ws.getState());
            webSocketReadyCallback.onReady(this);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        ws.sendText(message);
    }

    public void sendBinary(byte [] binary){
        ws.sendFrame(WebSocketFrame.createBinaryFrame(binary));
    }
}
