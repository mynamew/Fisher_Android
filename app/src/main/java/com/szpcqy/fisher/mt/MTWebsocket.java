package com.szpcqy.fisher.mt;


import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MTWebsocket {

    public interface OnCallback {
        void onOpen(WebSocketClient client);

        void onClose(String reason, boolean isRemote);

        void onError(String reason);

        void onMessage(WebSocketClient client, String msg);
    }

    private String m_ws;
    private OnCallback m_callback;
    WebSocketClient m_socket;

    static public MTWebsocket connect(String ws, OnCallback callbak) {
        return new MTWebsocket(ws, callbak);
    }

    public void close() {
        if (m_socket != null && !m_socket.isClosed()) {
            m_socket.close();
            Log.d("MT", "主动断开csocket");
        }
    }

    public void send(byte[] body) {
        if (m_socket != null && m_socket.isOpen()) {
            m_socket.send(body);
        }
    }

    public void send(String body) {
        if (m_socket != null && m_socket.isOpen()) {
            m_socket.send(body);
        }
    }

    private MTWebsocket(String ws, OnCallback callbak) {
        m_ws = ws;
        m_callback = callbak;

        initSocket();
    }

    private void initSocket() {
        try {
            m_socket = new WebSocketClient(new URI(m_ws)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    if (m_callback != null) {
                        m_callback.onOpen(m_socket);
                    }
                }

                @Override
                public void onMessage(String message) {
                    if (m_callback != null) {
                        m_callback.onMessage(m_socket, message);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if (m_callback != null) {
                        m_callback.onClose(reason, remote);
                    }
                }

                @Override
                public void onError(Exception ex) {
                    if (m_callback != null) {
                        m_callback.onError(ex.getMessage());
                    }
                }
            };
            m_socket.connect();
        } catch (URISyntaxException e) {
            if (m_callback != null) {
                m_callback.onError("地址错误");
            }
        }
    }

    /**
     * 获取是否正在连接
     * @return
     */
    public boolean isConnectiong() {
        return m_socket.isConnecting();
    }
}
