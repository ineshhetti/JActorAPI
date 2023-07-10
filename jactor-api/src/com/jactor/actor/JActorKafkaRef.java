package com.jactor.actor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
public class JActorKafkaRef {
    private final String actorName;
    private final String topic;
    private final KafkaProducer<String, Object> producer;

    public JActorKafkaRef(String actorName, String topic) {
        this.actorName = actorName;
        this.topic = topic;

        // Create Kafka producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    public void sendMessage(Object message) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, actorName, message);
        producer.send(record);
    }
}
