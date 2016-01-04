package com.jgraham.server.ServerConnection;


public interface iHttpServerSocket {
    public iHttpSocket accept() throws Exception;
    public void close() throws Exception;
}