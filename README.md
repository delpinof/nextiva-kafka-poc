# Getting Started

### Pre-requisites

1. Install docker
2. To start kafka, go into [kafka-docker-local](kafka-docker-local) folder and run:

``` bash
docker-compose up
```

3. (Optional) If you want to go into the kafka broker, run in a new terminal:

``` bash
 docker exec -it broker-local /bin/sh
 ```

#### Here you will be able to execute any Kafka CLI commands :)

### Build

``` bash 
mvn clean install
```

### Run

Both consumer and producer are spring boot applications.

* Run [KafkaPoducerApplication](kafka-producer/src/main/java/com/nextiva/poc/kafka/producer/KafkaProducerApplication.java)
* Run [KafkaConsumerApplication](kafka-consumer/src/main/java/com/nextiva/poc/kafka/consumer/KafkaConsumerApplication.java)

### Execution

#### Publish a string message:
``` bash
curl --location --request GET 'http://localhost:8080/kafka/publish?message=hello%20world!'
```

#### Publish a json message:
``` bash
curl --location --request POST 'http://localhost:8080/kafka/user' --header 'Content-Type: application/json' --data-raw '{"id":"95a1af9d-fe89-4d3e-a274-687ba3c045d5","salary":150}'
```

#### Start an inifinite producer:
``` bash
curl --location --request POST 'http://localhost:8080/kafka/launch' --header 'Content-Type: application/json'
```

### Now watch for logs :)

---

## Useful commands

#### Console Producer
``` bash
kafka-console-producer --bootstrap-server localhost:9092 --topic kafka-example-string
```
#### Console Consumer
``` bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka-example-string --property print.key=true --property key.separator="-" --from-beginning
```
#### List Consumer Groups
``` bash
kafka-consumer-groups --bootstrap-server localhost:9092 --list
```
#### Describe a Consumer Group
```bash 
kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group string-message-group1
```
#### Repartition a topic
``` bash
kafka-topics --bootstrap-server localhost:9092 --topic kafka-example-json --alter --partitions 10
```