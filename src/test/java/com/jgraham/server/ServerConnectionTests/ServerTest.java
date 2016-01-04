package com.jgraham.server.ServerConnectionTests;

import com.jgraham.server.Requests.Request;
import com.jgraham.server.ResponseBuilder.iResponseBuilder;
import com.jgraham.server.ServerConnection.Server;
import com.jgraham.server.ServerConnection.iHttpSocket;
import com.jgraham.server.mocks.MockHttpServerSocket;
import com.jgraham.server.mocks.MockHttpSocket;
import com.jgraham.server.mocks.MockResponseBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class ServerTest {

    @Test
    public void acceptConnectionTest() throws Exception {
        MockHttpServerSocket serverSocket = new MockHttpServerSocket(null);
        Server server = new Server(5000, null);
        iHttpSocket socket = server.acceptConnection(serverSocket);
        Assert.assertTrue(socket.isConnected());
    }

    @Test
    public void getRequestTest() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("foobar\r\n\r\nnext line".getBytes());
        ByteArrayOutputStream outputStream = null;
        MockHttpSocket mockSocket = new MockHttpSocket(inputStream, outputStream);
        Server server = new Server(5000, null);
        Assert.assertTrue(server.getRawRequest(mockSocket).contains("foobar\r\n\r\nnext line"));
    }

    @Test
    public void writeResponseTest() throws Exception {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MockHttpSocket mockSocket = new MockHttpSocket(inputStream, outputStream);
        Server server = new Server(5000, null);
        server.writeResponse(mockSocket, "HTTP/1.1 200 OK\r\n\r\nresponse to be written".getBytes());
        Assert.assertTrue(outputStream.toByteArray().length > 0);
        Assert.assertTrue(outputStream.toString().contains("HTTP/1.1 200 OK\r\n\r\nresponse to be written"));
    }

    @Test
    public void getResponseTest() throws Exception {
        iResponseBuilder responseBuilder = new MockResponseBuilder("test response");
        Server server = new Server(5000, null);
        Assert.assertEquals("test response", new String(server.getResponse(responseBuilder)));
    }

    @Test
    public void getNewRequestTest() throws Exception {
        Server server = new Server(5000, null);
        Assert.assertEquals(server.getNewRequest("GET / HTTP/1.1\r\n\r\n").getClass(), new Request(new HashMap<String, String>()).getClass());
    }

}
