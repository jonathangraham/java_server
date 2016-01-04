package com.jgraham.server.RequestsTests;

import com.jgraham.server.Requests.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RequestTest {


    @Test
    public void returnsParsedRequestComponents() {
        Map<String, String> parsedRequestComponents = new HashMap<>();
        parsedRequestComponents.put("RequestType", "GET");
        parsedRequestComponents.put("RequestURL", "/");
        parsedRequestComponents.put("RequestHTTPVersion", "HTTP/1.1");
        parsedRequestComponents.put("RequestHeader", "Header");
        Request request = new Request(parsedRequestComponents);
        Assert.assertEquals("GET", request.getRequestType());
        Assert.assertEquals("/", request.getRequestURL());
        Assert.assertEquals("HTTP/1.1", request.getHTTPVersion());
        Assert.assertEquals("Header", request.getRequestHeader());
    }
}
