package com.jactor.http;

import com.jactor.actor.JActor;
import com.jactor.actor.JActorRef;


public class HttpActor implements JActor {
    @Override
    public void receive(JActorRef sender, Object message) {
        if (message instanceof HttpRequest) {
            handleHttpRequest(sender, (HttpRequest) message);
        } else {
            // Handle other message types if needed
        }
    }

    private void handleHttpRequest(JActorRef sender, HttpRequest request) {
        // Process the HTTP request and generate a response
        HttpResponse response = processRequest(request);

        // Send the response back to the sender
        sender.sendMessage(response);
    }

    private HttpResponse processRequest(HttpRequest request) {
        // Process the HTTP request and generate an appropriate response
        // ...

        // For demonstration purposes, let's just return a simple response
        String responseBody = "Hello, World!";
        return new HttpResponse(200, "OK", null, responseBody);
    }
}

