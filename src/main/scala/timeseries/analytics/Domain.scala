package timeseries.analytics

case class InputLine(timestamp: Int, priceRatio: Double)

case class TimeSeriesStats(endTimestamp: Int, priceRatio: Double, numberMeasurements: Int, rollingSum: Double,
                           minimumPriceRatio: Double, maximumPriceRatio: Double) {
  override def toString() =
    s"""$endTimestamp $priceRatio $numberMeasurements ${f"$rollingSum%1.5f"} $minimumPriceRatio $maximumPriceRatio \n"""
}
