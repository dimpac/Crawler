package im.paz.etl.example

import im.paz.etl.example.input.TheGuardianSpider
import im.paz.etl.example.output.TheGuardianProducer

object TheGuardianCrawler {

  def main(args: Array[String]) {

    val x = new TheGuardianSpider
    val y = new TheGuardianProducer

    val siteMap = scala.collection.mutable.HashMap.empty[String, (String, Int)]

    val z = x.generateSiteMap(siteMap, (a: String) => x.allLinks(a), x.url, 1)

    for (i <- z){
      val doc = x.document(i._1) match {
        case Left(d) => d.body.outerHtml()
        case Right(e) => e
      }

      y.send(i._1, doc)
    }
  }

}
