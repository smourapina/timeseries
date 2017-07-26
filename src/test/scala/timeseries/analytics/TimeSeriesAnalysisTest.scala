package timeseries.analytics

import org.scalatest._

class TimeSeriesAnalysisTest extends FlatSpec with Matchers {

  val analysis = new TimeSeriesAnalysis

  val inputLine1 = InputLine(1000, 1.00)
  val inputLine2 = InputLine(1010, 2.00)
  val inputLine3 = InputLine(1020, 3.0)
  val testWindow = WindowStats(1000, 1.5, Seq(inputLine1, inputLine2, inputLine3))

  "TimeSeriesAnalysis" should "correctly calculate time series stats" in {
    analysis.calculateTimeSeriesStats(Stream(InputLine(1000, 1.00), InputLine(1100, 2.00), InputLine(1100, 3.00)))

    val expected = StringBuilder.newBuilder
    expected.append(TimeSeriesStats(1000, 1.0, 1, 1.00000, 1.00, 1.00))
    expected.append(TimeSeriesStats(1100, 2.0, 1, 2.00000, 2.00, 2.00))
    expected.append(TimeSeriesStats(1100, 3.0, 2, 5.00000, 2.00, 3.00))

    assert(analysis.timeSeriesStatsResult.toString == expected.toString)
  }

  "WindowStats" should "correctly calculate time series stats for a given window" in {
    val calculated = testWindow.calculateWindowStats()
    assert(calculated == TimeSeriesStats(1000, 1.5, 3, 6.00, 1.00, 3.00))
  }

  "WindowStats" should "correctly filter by timestamp" in {
    val result = testWindow.filterByTimeStamp(1010)
    assert(result == Seq(inputLine3))
  }

}
