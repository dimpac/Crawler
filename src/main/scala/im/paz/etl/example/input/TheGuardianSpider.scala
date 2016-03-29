package im.paz.etl.example.input

import etl.toolbelt.core.JsoupInput

class TheGuardianSpider extends JsoupInput {

  val protocol = "http://"
  val prefix = "www"
  val host = "theguardian.com"
  val suffix = "au"
  val agent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"

}
