package config

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig.{BOOTSTRAP_SERVERS_CONFIG, KEY_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER_CLASS_CONFIG}
import org.apache.kafka.common.serialization.StringSerializer

object BrokerConfig {

  val BROKERS = "localhost:9092"

  def getDefaultConfig : Properties = {
    val config = new Properties()

    /** For fault tolerance, more than one broker should be mentioned.
     * Producer doesn't connect to all servers but first available one.  */
    config.put(BOOTSTRAP_SERVERS_CONFIG, BROKERS)
    config.put(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config.put(VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config
  }

}
