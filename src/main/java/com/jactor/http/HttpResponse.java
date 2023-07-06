package com.jactor.http;

import io.netty.handler.codec.http.HttpHeaders;

public class HttpResponse {
    private final int status;
    private final String statusText;
    private final HttpHeaders headers;
    private final String body;

    public HttpResponse(int status, String statusText, HttpHeaders headers, String body) {
        this.status = status;
        this.statusText = statusText;
        this.headers = headers;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
