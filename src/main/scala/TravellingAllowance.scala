import java.text.SimpleDateFormat

import org.joda.time.{Days, DateTime}
import org.joda.time.DateTimeConstants._

import scala.io.StdIn

object TravellingAllowance extends App {

  println("Input start date(yyyy-MM-dd): ")

  val startDate = parseDate(readLine())

  println("Input end date(yyyy-MM-dd, default today): ")

  val endDate = Option(readLine()).filter(_.trim.length > 0).map(parseDate).getOrElse(new DateTime)

  println("Input money per weekday(150): ")

  val perWeekday = Option(readLine()).filter(_.length > 0).map(_.toInt).getOrElse(150)

  println("Input money per weekend-day(350): ")

  val perWeekend = Option(readLine()).filter(_.length > 0).map(_.toInt).getOrElse(350)


  println("########################################")
  println(s"${short(startDate)} ~ ${short(endDate)}")
  println(s"weekday: $perWeekday, weekend: $perWeekend")
  println()
  println(s"total days: $totalDays")
  println(s"weekday count: $weekdayCount, weekend day count: $weekendDayCount")
  println(s"Total: $total")
  println()
  println(s"${short(startDate)} ~ ${short(endDate)}, $totalDays days, $perWeekday*$weekdayCount+$perWeekend*$weekendDayCount=$total")

  private def total = weekdayCount * perWeekday + weekendDayCount * perWeekend
  private def totalDays = Days.daysBetween(startDate.toLocalDate, endDate.toLocalDate).getDays
  private def weekdayCount = totalDays - weekendDayCount
  private def weekendDayCount = (startDate.getDayOfYear to endDate.getDayOfYear).count(isWeekend)

  private def parseDate(str: String): DateTime = {
    new DateTime(format.parse(str))
  }

  private def format = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    format.setLenient(false)
    format
  }

  private def isWeekend(index: Int): Boolean = {
    val ddd = startDate.minusDays(index).getDayOfWeek
    ddd == SATURDAY || ddd == SUNDAY
  }


  private def readLine() = StdIn.readLine().trim

  private def short(date: DateTime): String = {
    format.format(date.toDate)
  }
}
