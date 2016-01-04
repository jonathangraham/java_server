package com.jgraham.server.mocks;

import com.jgraham.server.ResponseBuilder.iResponseBuilder;

public class MockResponseBuilder implements iResponseBuilder {

    private byte[] response;

    public MockResponseBuilder(String response) {
        this.response = response.getBytes();
    }

    public byte[] getResponse() {
        return response;
    }

    public void modifyFile() throws Exception {}
}
