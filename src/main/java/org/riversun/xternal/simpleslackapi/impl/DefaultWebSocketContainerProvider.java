package org.riversun.xternal.simpleslackapi.impl;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.riversun.xternal.simpleslackapi.WebSocketContainerProvider;

import javax.websocket.WebSocketContainer;

public class DefaultWebSocketContainerProvider implements WebSocketContainerProvider
{

    private String proxyAddress;
    private int proxyPort;

    DefaultWebSocketContainerProvider(String proxyAdress, int proxyPort) {
        this.proxyAddress = proxyAdress;
        this.proxyPort = proxyPort;
    }

    @Override
    public WebSocketContainer getWebSocketContainer()
    {
        ClientManager clientManager = ClientManager.createClient();
        if (proxyAddress != null)
        {
            clientManager.getProperties().put(ClientProperties.PROXY_URI, "http://" + proxyAddress + ":" + proxyPort);
        }
        return clientManager;
    }
}
