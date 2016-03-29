import Dependencies._

name := "Crawler"

version := "0.1"

scalaVersion := "2.11.6"

libraryDependencies in ThisBuild ++= coreDeps ++ testDeps


scalastyleConfig := file("project/scalastyle_config.xml")

resolvers += Resolver.bintrayRepo("dimpac", "maven")