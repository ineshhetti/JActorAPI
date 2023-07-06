package com.jactor.actor;

public interface SupervisorStrategy {
    void handleFailure(Exception exception, JActorRef actorRef);
}