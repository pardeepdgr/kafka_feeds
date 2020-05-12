package config

import java.util.Properties

import org.apache.kafka.clients.CommonClientConfigs.{BOOTSTRAP_SERVERS_CONFIG, GROUP_ID_CONFIG}
import org.apache.kafka.clients.consumer.ConsumerConfig.{AUTO_COMMIT_INTERVAL_MS_CONFIG, ENABLE_AUTO_COMMIT_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG}
import org.apache.kafka.clients.producer.ProducerConfig.{KEY_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER_CLASS_CONFIG}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

object BrokerConfig {
  private val BROKERS = "localhost:9092"
  private val CONSUMER_GROUP = "grp"
  private val ENABLED = "true"
  private val ONE_SEC = "1000"

  def getProducerConfigs: Properties = {
    val config = new Properties()
    /** For fault tolerance, more than one broker should be mentioned.
     * Producer doesn't connect to all servers but first available one.  */
    config.put(BOOTSTRAP_SERVERS_CONFIG, BROKERS)
    config.put(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config.put(VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    config
  }

  def getConsumerConfigs: Properties = {
    val config = new Properties()
    config.put(BOOTSTRAP_SERVERS_CONFIG, BROKERS)
    config.put(KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    config.put(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    config.put(GROUP_ID_CONFIG, CONSUMER_GROUP)
    config.put(ENABLE_AUTO_COMMIT_CONFIG, ENABLED)
    config.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, ONE_SEC)
    config
  }

}
