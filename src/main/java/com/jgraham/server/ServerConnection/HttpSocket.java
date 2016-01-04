package com.jgraham.server.ServerConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSocket implements iHttpSocket {

    private Socket socket;

    public HttpSocket(ServerSocket serverSocket) throws Exception {
        Socket clientSocket = serverSocket.accept();
        this.socket = clientSocket;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws Exception {
        return socket.getOutputStream();
    }

    @Override
    public Boolean isConnected() throws Exception {
        return socket.isConnected();
    }
}
