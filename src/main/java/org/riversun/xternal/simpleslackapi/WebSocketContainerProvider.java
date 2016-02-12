package org.riversun.xternal.simpleslackapi;

import javax.websocket.WebSocketContainer;

public interface WebSocketContainerProvider
{
    WebSocketContainer getWebSocketContainer();
}
