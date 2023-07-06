package com.jactor;

import java.util.HashMap;
import java.util.Map;

public class JActorSystem {
    private Map<String, JActorRef> actors;
    private SupervisorStrategy supervisorStrategy;
    public JActorSystem() {
        this.actors = new HashMap<>();
    }

    public JActorSystem(SupervisorStrategy supervisorStrategy) {
        this.supervisorStrategy = supervisorStrategy;
        this.actors = new HashMap<>();
    }

    public void setSupervisorStrategy(SupervisorStrategy supervisorStrategy) {
        this.supervisorStrategy = supervisorStrategy;
    }

    public JActorRef createActor(String actorName, JActor actor) {
        JActorRef actorRef = new JActorRef(actorName, actor,supervisorStrategy);
        actors.put(actorName, actorRef);
        return actorRef;
    }

    public void sendMessage(String actorName, Object message) {
        JActorRef actorRef = actors.get(actorName);
        if (actorRef != null) {
            actorRef.sendMessage(message);
        } else {
            throw new IllegalArgumentException("Actor " + actorName + " not found.");
        }
    }

    public void shutdown() {
        for (JActorRef actorRef : actors.values()) {
            actorRef.stop();
        }
        actors.clear();
    }
}
