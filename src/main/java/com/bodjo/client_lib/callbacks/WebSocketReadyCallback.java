package com.bodjo.client_lib.callbacks;

import com.bodjo.client_lib.ws.WebSocketController;

public interface WebSocketReadyCallback {
    void onReady(WebSocketController webSocketController);
}
