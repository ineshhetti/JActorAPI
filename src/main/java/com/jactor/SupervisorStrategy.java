package com.jactor;

public interface SupervisorStrategy {
    void handleFailure(Exception exception, JActorRef actorRef);
}