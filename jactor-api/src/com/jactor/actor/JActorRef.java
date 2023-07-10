package com.jactor.actor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JActorRef {
    private final String actorName;
    private final JActor actor;
    private final BlockingQueue<Message> messageQueue;

    private final JActorRouter actorRouter;
    private volatile boolean running;

    private Thread actorThread;
    private final SupervisorStrategy supervisorStrategy;

    public void restart() {
        stop();
        clearMessageQueue();
        resetRestartCount();
        startMessageProcessingThread();
    }

    private int restartCount;

    public JActorRef(String actorName, JActor actor, SupervisorStrategy supervisorStrategy) {
        this.actorName = actorName;
        this.actor = actor;
        this.messageQueue = new LinkedBlockingQueue<>();
        this.supervisorStrategy = supervisorStrategy;
        this.running = true;
        this.actorRouter = null;
        startMessageProcessingThread();
    }

    public String getActorName() {
        return actorName;
    }

    private void clearMessageQueue() {
        messageQueue.clear();
    }

    private void resetRestartCount() {
        restartCount = 0;
    }

    public void sendMessage(Object message) {
        if (actorRouter != null) {
            actorRouter.routeMessage(message);
        } else if (messageQueue != null) {
            messageQueue.add(new Message(this, message));
        }
    }

    public void stop() {
        running = false;
        actorThread.interrupt();
    }

    public int getNumRestarts() {
        return restartCount;
    }

    public void incrementRestartCount() {
        restartCount++;
    }

    private void startMessageProcessingThread() {
        actorThread = Thread.ofVirtual()
                .unstarted(() -> {
                    while (running) {
                        try {
                            Message message = messageQueue.take();
                            actor.receive(message.getSender(), message.getContent());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            if (supervisorStrategy != null) {
                                supervisorStrategy.handleFailure(e, this);
                            } else {
                                System.out.println("No Supervisor Strategy Defined for : " + actorName);
                            }
                        }
                    }
                });
        actorThread.setName(actorName);
        actorThread.start();
    }
}
