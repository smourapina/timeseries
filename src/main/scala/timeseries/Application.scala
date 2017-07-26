package timeseries

import timeseries.analytics.TimeSeriesAnalysis
import timeseries.io.{InputProcessor, ResultWriter}

import scala.util.Success
import scala.util.Failure

object Application extends InputProcessor with ResultWriter {

  def main(args: Array[String]) {
    print("Please enter the location of the input file: ")

    val filePath = scala.io.StdIn.readLine() // Example: data/data_scala.txt

    val analysis = new TimeSeriesAnalysis

    getTimeSeriesDataAsStream(filePath) match {
      case Success(data) => {
        analysis.calculateTimeSeriesStats(data)
        printResultsToConsole(analysis.timeSeriesStatsResult)
      }
      case Failure(ex) => println(s"Problem reading file content: ${ex.getMessage}")
    }
  }

}