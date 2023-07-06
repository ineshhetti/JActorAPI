package com.jactor.http;

import com.jactor.actor.JActorSystem;

public class Main {
    public static void main(String[] args) {
        // Create an actor system
        JActorSystem actorSystem = new JActorSystem();

        // Create an HTTP server
        HttpServer httpServer = new HttpServer(8080, actorSystem);

        // Start the server
        try {
            httpServer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the actor system gracefully
        actorSystem.shutdown();
    }
}