package com.jgraham.server.ServerConnection;

import java.net.ServerSocket;

public class HttpServerSocket implements iHttpServerSocket {
    private ServerSocket serverSocket;

    public HttpServerSocket(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        this.serverSocket = serverSocket;
    }

    @Override
    public iHttpSocket accept() throws Exception {
        iHttpSocket clientSocket = new HttpSocket(serverSocket);
        return clientSocket;
    }

    @Override
    public void close() throws Exception {
        serverSocket.close();
    }
}
