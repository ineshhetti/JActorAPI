package com.jactor;

public interface JActor {
    void receive(JActorRef sender, Object message);
}