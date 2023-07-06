package com.jactor;

import java.util.HashSet;
import java.util.Set;

public class ActorSelectionPattern {
    private final Set<JActorRef> actors;
    private SupervisorStrategy supervisorStrategy;
    public ActorSelectionPattern() {
        this.actors = new HashSet<>();
    }
    public ActorSelectionPattern(SupervisorStrategy supervisorStrategy) {
        this.supervisorStrategy = supervisorStrategy;
        this.actors = new HashSet<>();
    }
    public JActorRef createActor(String actorName, JActor actor) {
        JActorRef actorRef = new JActorRef(actorName, actor, this.supervisorStrategy);
        actors.add(actorRef);
        return actorRef;
    }

    public Set<JActorRef> selectActorsByPattern(String pattern) {
        Set<JActorRef> selectedActors = new HashSet<>();
        for (JActorRef actorRef : actors) {
            if (actorRef.getActorName().matches(pattern)) {
                selectedActors.add(actorRef);
            }
        }
        return selectedActors;
    }
}
