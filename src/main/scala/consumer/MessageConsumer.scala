package consumer

import java.time.Duration
import java.util

import config.BrokerConfig.getConsumerConfigs
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

class MessageConsumer {

  def subscribeToAllPartitionsOf(topics: util.List[String]): Unit = {
    val consumer = createConsumer

    try {
      consumer.subscribe(topics)
      while (true) {
        /* poll operation opens network resources so put it inside try-catch */
        val messages = consumer.poll(Duration.ofMillis(10))
        processMessages(messages)
      }
    } finally {
      topics.clear()
      consumer.subscribe(topics)
      consumer.close()
    }
  }

  def subscribeToSpecificPartitionOf(topic: String, partitionNumber: Int): Unit = {
    val partitions = getPartitions(topic, partitionNumber)
    val consumer = createConsumer

    try {
      consumer.assign(partitions)
      while (true) {
        val messages = consumer.poll(Duration.ofMillis(10))
        processMessages(messages)
      }
    } finally {
      consumer.unsubscribe()
      consumer.close()
    }
  }

  private def createConsumer: KafkaConsumer[String, String] = {
    val configs = getConsumerConfigs
    val consumer = new KafkaConsumer[String, String](configs)
    consumer
  }

  private def processMessages(messages: ConsumerRecords[String, String]): Unit = {
    messages.forEach(
      msg => println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
        msg.topic, msg.partition(), msg.offset(), msg.key(), msg.value())))
  }

  private def getPartitions(topic: String, partitionNumber: Int): util.ArrayList[TopicPartition] = {
    val partitions = new util.ArrayList[TopicPartition]()
    partitions.add(new TopicPartition(topic, partitionNumber))
    partitions
  }

}
