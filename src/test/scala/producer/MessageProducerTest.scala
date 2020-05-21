package producer

import base.TestBootstrap

class MessageProducerTest extends TestBootstrap {

  it should "create a kafka producer send message" in {
    val producer = new MessageProducer
    producer.sendTo("feeds", "1", "test msg")
  }
}
