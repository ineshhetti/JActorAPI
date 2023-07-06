package com.jactor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Creating the publisher actor
        PublisherActorPattern publisher = new PublisherActorPattern();
        JActorSystem system = new JActorSystem(new RestartSupervisorStrategy(100, 100));
        // Creating subscriber actors


        JActorRef subscriber1 = system.createActor("Subscriber1", new JActor() {
            @Override
            public void receive(JActorRef sender, Object message) {
                System.out.println("Subscriber1 received message: " + message);
            }
        });
        JActorRef subscriber2 = system.createActor("Subscriber2", new JActor() {
            @Override
            public void receive(JActorRef sender, Object message) {
                System.out.println("Subscriber2 received message: " + message);
            }
        });

        // Subscribing actors to topics
        publisher.subscribe("topic1", subscriber1);
        publisher.subscribe("topic2", subscriber2);
        publisher.subscribe("topic2", subscriber1);

        // Publishing messages to topics
        publisher.publish("topic1", "Hello from topic1!");
        publisher.publish("topic2", "Hello from topic2!");

        // Unsubscribing actors from topics
        publisher.unsubscribe("topic1", subscriber1);

        // Publishing another message after unsubscribing
        publisher.publish("topic1", "This message won't reach subscriber1");
        for (int i = 0; i < 1000; i++) {
            publisher.publish("topic1", "Hello from topic1!");
            publisher.publish("topic2", "Hello from topic2!");
        }

        for (int i = 0; i < 10000; i++) {

        }
        System.out.println("end");


    }
}