package com.jactor;

public class Message {
    private final JActorRef sender;
    private final Object content;

    public Message(JActorRef sender, Object content) {
        this.sender = sender;
        this.content = content;
    }

    public JActorRef getSender() {
        return sender;
    }

    public Object getContent() {
        return content;
    }
}
