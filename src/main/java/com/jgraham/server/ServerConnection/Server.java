package com.jgraham.server.ServerConnection;

import com.jgraham.server.Requests.Request;
import com.jgraham.server.Requests.RequestParser;
import com.jgraham.server.ResponseBuilder.iResponseBuilder;
import com.jgraham.server.Routers.iAppRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Server {

    private iAppRouter appRouter;
    private int port;

    public Server(int port, iAppRouter appRouter) {
        this.port = port;
        this.appRouter = appRouter;
    }

    public void start() throws Exception {
        iHttpServerSocket serverSocket = new HttpServerSocket(port);
        while (true) {
            iHttpSocket clientSocket = acceptConnection(serverSocket);
            actOnConnection(clientSocket);
        }
    }

    public void actOnConnection(iHttpSocket clientSocket) throws Exception {
        String rawRequest = getRawRequest(clientSocket);
        Request request = getNewRequest(rawRequest);
        iResponseBuilder responseBuilder = getResponseBuilder(appRouter, request);
        byte[] response = getResponse(responseBuilder);
        writeResponse(clientSocket, response);
    }

    public String getRawRequest(iHttpSocket clientSocket) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = "";
        try {
            request += (char) in.read();
            while (in.ready()) {
                request += (char) in.read();
            }
            return request;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading request";
        }
    }

    public Request getNewRequest(String rawRequest) {
        RequestParser requestParser = new RequestParser();
        requestParser.generateParsedRequest(rawRequest);
        Request request = requestParser.getNewRequest();
        return request;
    }

    public iResponseBuilder getResponseBuilder(iAppRouter responseRoute, Request request) throws Exception {
        return responseRoute.getResponseBuilder(request);
    }

    public byte[] getResponse(iResponseBuilder responseBuilder) throws Exception {
        return responseBuilder.getResponse();
    }

    public void writeResponse(iHttpSocket clientSocket, byte[] response) throws Exception {
        OutputStream out = clientSocket.getOutputStream();
        out.write(response);
        out.close();
    }

    public iHttpSocket acceptConnection(iHttpServerSocket serverSocket) throws Exception {
        return serverSocket.accept();
    }
}
