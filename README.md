# Microservices with Spring and Kafka
This project is based upon Spring Boot, Docker and Apache Kafka.

## Structure
The project currently consists of three modules:
- config-data,
- documents-to-kafka-service,
- kafka,
    - kafka-admin,
    - kafka-model,
    - kafka-producer.

The purpose of each module is described in the following chapters.

### Config-Data
This module holds config data for all other modules

### Documents-to-kafka-service
This module is the central microservice that produces kafka messages. Therefore, it mocks a stream that sends a document object to kafka every 10 seconds

### Kafka
This module consists of three sub-modules:
- admin,
- model,
- producer.
  The admin module creates kafka topics and checks their creation and the schema registry's availability. The model module uses the avro maven plugin to
  create the object that is sent via kafka. The producer module describes the sending of data via kafka  
