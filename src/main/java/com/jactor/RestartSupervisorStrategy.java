package com.jactor;

public class RestartSupervisorStrategy implements SupervisorStrategy {
    private final int maxRestartCount;
    private final long restartDelayMillis;
    public static RestartSupervisorStrategy createDefault(){
        return new RestartSupervisorStrategy(10,100);
    }
    public RestartSupervisorStrategy(int maxRestartCount, long restartDelayMillis) {
        this.maxRestartCount = maxRestartCount;
        this.restartDelayMillis = restartDelayMillis;
    }

    @Override
    public void handleFailure(Exception exception, JActorRef actorRef) {
        if (actorRef.getNumRestarts() < maxRestartCount) {
            // Restart the actor after a delay
            actorRef.incrementRestartCount();
            try {
                Thread.sleep(restartDelayMillis);
            } catch (InterruptedException e) {
                // Handle or log the exception
            }
            actorRef.restart();
        } else {
            // Stop the actor if the maximum restart count is reached
            actorRef.stop();
        }
    }
}