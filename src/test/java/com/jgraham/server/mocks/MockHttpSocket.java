package com.jgraham.server.mocks;

import com.jgraham.server.ServerConnection.iHttpSocket;

import java.io.InputStream;
import java.io.OutputStream;

public class MockHttpSocket implements iHttpSocket {

    private InputStream mockInput;
    private OutputStream mockOutput;

    public MockHttpSocket(InputStream mockInput, OutputStream mockOutput) {
        this.mockInput = mockInput;
        this.mockOutput = mockOutput;
    }

    public InputStream getInputStream() throws Exception {
        return mockInput;
    }

    public OutputStream getOutputStream() throws Exception {
        return mockOutput;
    }

    public Boolean isConnected() throws Exception {
        return true;
    }

}
