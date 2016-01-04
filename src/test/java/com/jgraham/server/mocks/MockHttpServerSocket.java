package com.jgraham.server.mocks;

import com.jgraham.server.ServerConnection.iHttpServerSocket;
import com.jgraham.server.ServerConnection.iHttpSocket;

public class MockHttpServerSocket implements iHttpServerSocket {
    private iHttpSocket mockSocket;
    private boolean wasClosed = false;


    public MockHttpServerSocket(iHttpSocket mockSocket) {
        this.mockSocket = mockSocket;
    }

    @Override
    public iHttpSocket accept() throws Exception {
        return  mockSocket = new MockHttpSocket(null, null);
    }

    @Override
    public void close() throws Exception {
        wasClosed = true;
    }

    public boolean wasClosed() {
        return wasClosed;
    }

}
