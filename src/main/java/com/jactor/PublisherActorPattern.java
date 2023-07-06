package com.jactor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PublisherActorPattern {
    private final Map<String, Set<JActorRef>> subscriptions;

    public PublisherActorPattern() {
        this.subscriptions = new HashMap<>();
    }

    public void subscribe(String topic, JActorRef subscriber) {
        subscriptions.computeIfAbsent(topic, key -> new HashSet<>()).add(subscriber);
    }

    public void unsubscribe(String topic, JActorRef subscriber) {
        Set<JActorRef> subscribers = subscriptions.get(topic);
        if (subscribers != null) {
            subscribers.remove(subscriber);
            if (subscribers.isEmpty()) {
                subscriptions.remove(topic);
            }
        }
    }

    public void publish(String topic, Object message) {
        Set<JActorRef> subscribers = subscriptions.get(topic);
        if (subscribers != null) {
            for (JActorRef subscriber : subscribers) {
                subscriber.sendMessage(message);
            }
        }
    }
}

