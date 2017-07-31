# KafkaClients
A project to implement Kafka Producer and Kafka Consumer

What is Kafka?
- Apache Kafka was originated at LinkedIn and later became an open sourced Apache project in 2011, then First-class Apache project in 2012. Kafka is written in Scala and Java. Apache Kafka is publish-subscribe based fault tolerant messaging system. It is fast, scalable and distributed by design.
- Kafka is distributed, highly scalable messaging system which can transfer huge volume of data and also provides fault tolerance via replication.
- Kafka doesn't need to connect to any data source to maintain metadata and user data. It maintains data in log files (flat files) on the servers itself. It is very fast - 2M transactions/second.

Kafka is based on publish and subscribe model. In general there are two models available
	1. Point to point 
	2. Publish subscribe

Core concepts of Kafka
Key components of Kafka system are:
	1. Producer
	2. Consumer
	3. Kafka Broker
	4. Topics

Some other concepts:

    1. Partitions: Kafka topics are divided into a number of partitions. Partitions allow you to parallelize a topic by splitting the data in a particular topic across multiple brokers — each partition can be placed on a separate machine to allow for multiple consumers to read from a topic in parallel. Consumers can also be parallelized so that multiple consumers can read from multiple partitions in a topic allowing for very high message processing throughput.
    2. Consumer Group: Consumers read from any single partition, allowing you to scale throughput of message consumption in a similar fashion to message production. Consumers can also be organized into consumer groups for a given topic — each consumer within the group reads from a unique partition and the group as a whole consumes all messages from the entire topic. If you have more consumers than partitions then some consumers will be idle because they have no partitions to read from. If you have more partitions than consumers then consumers will receive messages from multiple partitions. If you have equal numbers of consumers and partitions, each consumer reads messages in order from exactly one partition.
    3. ZooKeeper: A critical dependency of Apache Kafka is Apache Zookeeper, which is a distributed configuration and synchronization service. Zookeeper serves as the coordination interface between the Kafka brokers and consumers. The Kafka servers share information via a Zookeeper cluster. Kafka stores basic metadata in Zookeeper such as information about topics, brokers, consumer offsets (queue readers) and so on.
    4. Offset: Each message within a partition has an identifier called its offset. The offset the ordering of messages as an immutable sequence. Kafka maintains this message ordering for you. Consumers can read messages starting from a specific offset and are allowed to read from any offset point they choose, allowing consumers to join the cluster at any point in time they see fit.
  
  
  Algorithm for Producer client
  
	1. Configure properties using Properties
	2. Create ProducerRecord<Key,Value> using topic name, partition, key, and value
	3. Create a KafkaProducer class which is the main driving class.
	4. Send the Kafka message using send() method of KafkaProducer class.
	5. You may make use of Callback interface which provides onCompletion() method which is called for each successful message delivery.
	6. Finally close the kafkaProducer using close() method to avoid memory leaks.


Algorithm for Consumer client

  Here we create both consumers and consumers group. One group can consist of many consumers and all these consumers must be threads       running in parallel. 
    1. Create a consumer thread and configure all properties.
    2. Create a KafkaConsumer<key,value> and pass above properties to it.
    3. Call subscribe(topic_list) on the KafkaConsumer object.
    4. Create a KafkaRecord<key,value> object
    5. We generally run the Kafka consumer nonstop so create an infinite while loop and fetch records using
    kafkaRecordObject.poll(duration) method.
    6. Now you have records and you can manually commit the offset.


