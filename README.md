# Getting Started

### Pre-requisites

1. Install docker
2. Go into [kafka-docker-local](kafka-docker-local) folder and run:

  * `docker-compose up` to start kafka

3. In a new terminal, go into the kafka broker with:

* `docker exec -it broker-local /bin/sh`

#### Here you will be able to execute any Kafka CLI commands :)

### Build

`mvn clean install`

### Run

Both consumer and producer are spring boot applications.

* Run [KafkaPoducerApplication](kafka-producer/src/main/java/com/nextiva/poc/kafka/producer/KafkaProducerApplication.java)
* Run [KafkaConsumerApplication](kafka-consumer/src/main/java/com/nextiva/poc/kafka/consumer/KafkaConsumerApplication.java)

### Execution

#### Publish a string message:
`curl --location --request GET 'http://localhost:8080/kafka/publish?message=hello%20world!'`

#### Publish a json message:
`curl --location --request POST 'http://localhost:8080/kafka/user' --header 'Content-Type: application/json' --data-raw '{"id":"95a1af9d-fe89-4d3e-a274-687ba3c045d5","salary":150}'`

#### Start an inifinite producer:
`curl --location --request POST 'http://localhost:8080/kafka/launch' --header 'Content-Type: application/json'`

### Now watch for logs :)

---

## Useful commands

#### Console Producer
`kafka-console-producer --bootstrap-server localhost:9092 --topic kafka-example-string`
#### Console Consumer
`kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka-example-string --property print.key=true --property key.separator="-" --from-beginning`
#### List Consumer Groups
`kafka-consumer-groups --bootstrap-server localhost:9092 --list`
#### Describe a Consumer Group
`kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group string-message-group1`
#### Repartition a topic
`kafka-topics --bootstrap-server localhost:9092 --topic kafka-example-json --alter --partitions 10`