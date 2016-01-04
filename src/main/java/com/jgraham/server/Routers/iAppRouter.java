package com.jgraham.server.Routers;

import com.jgraham.server.Requests.Request;
import com.jgraham.server.ResponseBuilder.iResponseBuilder;

public interface iAppRouter {

    public iResponseBuilder getResponseBuilder(Request request) throws Exception;
}
