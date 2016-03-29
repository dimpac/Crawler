package im.paz.etl.example.output

import etl.toolbelt.core.KafkaOutput

class TheGuardianProducer extends  KafkaOutput{

  val kafkaServers = "localhost:9092"
  val topic = "theguardian"
  val keySerializer = "org.apache.kafka.common.serialization.StringSerializer"
  val valueSerializer = "org.apache.kafka.common.serialization.StringSerializer"

}
