package com.jactor;

import java.util.ArrayList;
import java.util.List;

/**
 * the ActorRouter class manages a list of routees (actors) and provides methods to add or remove
 * routees from the router. The routeMessage method implements a round-robin strategy to distribute
 * messages to the routees. It retrieves the next routee based on the current index and increments
 * the index for the next message.
 * <p>
 * To incorporate actor routing in your system, you can modify the ActorRef class to include a
 * reference to an ActorRouter. Then, when sending a message, you can use the ActorRouter to route
 * the message to the appropriate group of actors.
 */
public class JActorRouter {
    private final List<JActorRef> routees;
    private int currentIndex;

    public JActorRouter() {
        this.routees = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void addRoutee(JActorRef routee) {
        routees.add(routee);
    }

    public void removeRoutee(JActorRef routee) {
        routees.remove(routee);
    }

    public void routeMessage(Object message) {
        if (routees.isEmpty()) {
            // No routees available
            return;
        }

        JActorRef routee = getNextRoutee();
        routee.sendMessage(message);
    }

    private JActorRef getNextRoutee() {
        JActorRef routee = routees.get(currentIndex);
        currentIndex = (currentIndex + 1) % routees.size();
        return routee;
    }
}