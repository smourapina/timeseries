package timeseries.io

import timeseries.analytics.InputLine

import scala.io.Source
import scala.util.Try

trait InputProcessor {
  
  def getTimeSeriesDataAsStream(filePath: String): Try[Stream[InputLine]] = {

    Try {
      val data = Source.fromFile(filePath).getLines.map(line => {

        val input = line.split("""\s+""")
        (InputLine(input(0).toInt, input(1).toDouble))
      })

      data.toStream
    }
  }
}
