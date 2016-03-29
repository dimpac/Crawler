package im.paz.etl.example

import org.apache.spark.Logging
import im.paz.etl.example.input.TheGuardianConsumer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.spark._
import org.jsoup.Jsoup

case class ElasticRecord(url: String, date: String, title: String, author: String, body: String)

object KafkaStreamEtl extends Logging {

  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[2]").setAppName("CrawlingStreamETL")

    val ssc = new StreamingContext(conf, Seconds(1))
    val x = new TheGuardianConsumer(ssc)

    val y = x.inputStream

    y.foreachRDD{
      p => {
        val a = p.map{
          case q if Jsoup.parse(q._2).select("div.content__article-body").text != "" =>
            val c = Jsoup.parse(q._2)
            val title = c.select("h1.content__headline").text
            val author = c.select("p.byline").text
            val postDate = c.select("time").attr("datetime")
            val body = c.select("div.content__article-body").text
            ElasticRecord(q._1, postDate, title, author, body)
        }

        a.saveToEs("theguardian/articles")

      }
    }

    ssc.start()
    ssc.awaitTermination()
  }
}
