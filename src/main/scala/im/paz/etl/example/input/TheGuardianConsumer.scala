package im.paz.etl.example.input


import etl.toolbelt.core.SparkKafkaInput
import org.apache.spark.streaming.StreamingContext


class TheGuardianConsumer(ssc: StreamingContext) extends SparkKafkaInput(ssc){
  override val refreshRate: Int = 10
  override val kafkaCluster: String = "localhost:2181"
  override val partitions: Int = 1
  override val topic: String = "theguardian"
  override val consumerGroup: String = "0"
}
