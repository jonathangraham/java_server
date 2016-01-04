package com.jgraham.server.Requests;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    private String requestType = "";
    private String requestURL = "";
    private String requestHTTPVersion = "";
    private Map<String,String> parsedRequestComponents = new HashMap<>();
    private String requestHeader = "";

    public void generateParsedRequest(String rawRequest) {
        parseRequest(rawRequest);
        generateParsedRequestComponents();
    }

    public Request getNewRequest() {
        return new Request(parsedRequestComponents);
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public String getRequestHTTPVersion() {
        return requestHTTPVersion;
    }

    public Map<String,String> getParsedRequestComponents() {
        return parsedRequestComponents;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    private void parseRequest(String rawRequest) {
        String[] request = rawRequest.split("\r\n");
        parseRequestLine(request[0]);
        if (request.length > 1) {
            parseHeaderLine(request[1]);
        }
    }

    private void parseRequestLine(String request) {
        String[] requestLine = request.split(" ");
        requestType = requestLine[0];
        requestURL = requestLine[1];
        requestHTTPVersion = requestLine[2];
    }

    private void parseHeaderLine(String header) {
        requestHeader = header;
    }

    private void generateParsedRequestComponents() {
        parsedRequestComponents.put("RequestType", requestType);
        parsedRequestComponents.put("RequestURL", requestURL);
        parsedRequestComponents.put("RequestHTTPVersion", requestHTTPVersion);
        parsedRequestComponents.put("RequestHeader", requestHeader);
    }
}