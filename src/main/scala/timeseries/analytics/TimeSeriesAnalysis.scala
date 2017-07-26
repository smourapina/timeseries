package timeseries.analytics

class TimeSeriesAnalysis {

  val rollingWindowLength = 60

  val timeSeriesStatsResult = StringBuilder.newBuilder

  def calculateTimeSeriesStats(data: Stream[InputLine]) = {
    data.foldLeft(WindowStats(0, 0, List.empty[InputLine])) {

      (accum, current) => {

        val inputToProcess: Seq[InputLine] = accum.filterByTimeStamp(current.timestamp - rollingWindowLength) :+ current
        val currentWindow = WindowStats(current.timestamp, current.priceRatio, inputToProcess)

        val stats = currentWindow.calculateWindowStats()
        timeSeriesStatsResult.append(stats)

        currentWindow
      }
    }

  }
}

case class WindowStats(timestamp: Int, price: Double, values: Seq[InputLine]) {

  def filterByTimeStamp(time: Int): Seq[InputLine] = {
    values.filter(_.timestamp > time)
  }

  def calculateWindowStats(): TimeSeriesStats =
    TimeSeriesStats(timestamp, price, values.size,
      values.map(_.priceRatio).sum, values.map(_.priceRatio).min,
      values.map(_.priceRatio).max)
}