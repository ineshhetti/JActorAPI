package com.jactor.http;


import io.netty.handler.codec.http.HttpHeaders;

public class HttpRequest {
    private final String method;
    private final String path;
    private final HttpHeaders headers;
    private final String body;

    public HttpRequest(String method, String path, HttpHeaders headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
