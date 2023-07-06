package com.jactor.actor;

public interface JActor {
    void receive(JActorRef sender, Object message);
}