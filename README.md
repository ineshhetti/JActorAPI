# Actor API

The JActor API is a Java library that provides a lightweight actor framework for building concurrent and distributed 
applications. It allows you to create and manage actors, communicate between them using message passing, and implement fault-tolerant strategies for supervision and actor lifecycle.

## Features

- Actor creation and management
- Message passing between actors
- Actor supervision and fault tolerance
- Actor routing for load balancing and scalability
- Publish-Subscribe mechanism for decoupled communication
- Support for Java Virtual Threads (JEP 425)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 19 or higher

### Installation

You can include the Actor API in your project by adding the following Maven dependency:

```xml
<dependency>
    <groupId>com.jactor</groupId>
    <artifactId>jactor-api</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Usage

Here's a simple example of how to use the Actor API:

```java
import com.jactor.JActor;
import com.jactor.JActorRef;
import com.jactor.JActorSystem;

public class Main {
    public static void main(String[] args) {
        // Create an actor system
        JActorSystem actorSystem = new JActorSystem();

        // Create an actor
        JActorRef actor = actorSystem.createActor("MyActor", new MyActor());

        // Send a message to the actor
        actor.sendMessage("Hello, Actor!");

        // Stop the actor system
        actorSystem.shutdown();
    }
}

class MyActor implements Actor {
    @Override
    public void receive(JActorRef sender, Object message) {
        System.out.println("Received message: " + message);
    }
}

```
