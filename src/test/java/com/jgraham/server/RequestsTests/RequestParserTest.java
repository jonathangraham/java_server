package com.jgraham.server.RequestsTests;

import com.jgraham.server.Requests.Request;
import com.jgraham.server.Requests.RequestParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RequestParserTest {

    @Test
    public void returnsIndividualParsedRequestComponents() {
        RequestParser requestParser = new RequestParser();
        requestParser.generateParsedRequest("GET /partial_content.txt HTTP/1.1\r\nHeader: content\r\n");
        Assert.assertEquals("GET", requestParser.getRequestType());
        Assert.assertEquals("/partial_content.txt", requestParser.getRequestURL());
        Assert.assertEquals("HTTP/1.1", requestParser.getRequestHTTPVersion());
        Assert.assertEquals("Header: content", requestParser.getRequestHeader());
    }

    @Test
    public void returnsParsedRequestComponents() {
        RequestParser requestParser = new RequestParser();
        requestParser.generateParsedRequest("GET / HTTP/1.1");
        Map<String,String> testParsedRequestComponents = new HashMap<>();
        testParsedRequestComponents.put("RequestType", "GET");
        testParsedRequestComponents.put("RequestURL", "/");
        testParsedRequestComponents.put("RequestHTTPVersion", "HTTP/1.1");
        testParsedRequestComponents.put("RequestHeader", "");
        Assert.assertEquals(testParsedRequestComponents, requestParser.getParsedRequestComponents());
    }

    @Test
    public void generatesNewRequest() {
        RequestParser requestParser = new RequestParser();
        Request newRequest = requestParser.getNewRequest();
        Assert.assertNotNull(newRequest);
    }
}
