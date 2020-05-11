package producer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig.{BOOTSTRAP_SERVERS_CONFIG, KEY_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER_CLASS_CONFIG}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object MessageProducer {
  private val BROKERS = "localhost:9092"

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

  private def getDefaultConfig : Properties = {
    val config = new Properties()

    /** For fault tolerance, more than one broker should be mentioned.
     * Producer doesn't connect to all servers but first available one.  */
    config.put(BOOTSTRAP_SERVERS_CONFIG, BROKERS)
    config.put(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config.put(VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config
  }

}
