package producer

import config.BrokerConfig.getDefaultConfig
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object MessageProducer {

  def sendTo(topicName: String, key: String, msgContent: String) = {
    val producer = createProducer

    try{
      val msg = new ProducerRecord(topicName, key, msgContent)
      producer.send(msg)
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      producer.close()
    }
  }

  private def createProducer:KafkaProducer[String, String] = {
    val config = getDefaultConfig
    val producer = new KafkaProducer[String, String](config)
    producer
  }

}
