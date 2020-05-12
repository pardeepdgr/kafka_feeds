package consumer

import java.util

import base.TestBootstrap

class MessageConsumerTest extends TestBootstrap {

  it should "create a kafka consumer and subscribe to topics" in {
    val consumer = new MessageConsumer
    consumer.subscribeToAllPartitionsOf(util.Arrays.asList("feeds"))
  }

  it should "create a kafka consumer and subscribe to a particular partition of topic" in {
    val consumer = new MessageConsumer
    consumer.subscribeToSpecificPartitionOf("feeds", 1)
  }
}