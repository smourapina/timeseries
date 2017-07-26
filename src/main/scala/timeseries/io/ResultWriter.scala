package timeseries.io

import scala.collection.mutable

trait ResultWriter {

  private val resultHeader = "\nT \t\t   V \t   N  RS \t MinV \t MaxV \n" + ("-" * 44)

  def printResultsToConsole(stats: mutable.StringBuilder): Unit = {
    println(resultHeader)
    println(stats)
  }

}
