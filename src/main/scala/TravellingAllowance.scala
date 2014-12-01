import java.text.{ParseException, SimpleDateFormat}

import org.joda.time.{Days, DateTime}
import org.joda.time.DateTimeConstants._

import scala.io.StdIn

object TravellingAllowance extends App with DateParser with Calculator with InputReader {

  println("Input start date(yyyy-MM-dd, or MM-dd if it's in current year): ")

  val startDate = parseStartDate(readLine())

  println("Input end date(MM-dd or yyyy-MM-dd, use today if empty): ")

  val endDate = parseEndDate(readLine())

  println("Input allowance for weekday(¥150 if empty): ")

  val perWeekday = parseWeekdayAllowance(readLine(), 150)

  println("Input allowance for weekend(¥350 if empty): ")

  val perWeekend = parseWeekendAllowance(readLine(), 350)

  println(
    s"""
       |########################################
       |${display(startDate)} ~ ${display(endDate)}
       |
       |total days: $totalDays
       |weekday count: $weekdayCount, weekend day count: $weekendDayCount
       |
       |allowance for weekday: $perWeekday, weekend: $perWeekend
       |Total allowance: $total
       |
       |${display(startDate)} ~ ${display(endDate)}, $totalDays days, $perWeekday*$weekdayCount+$perWeekend*$weekendDayCount=$total
     """.trim.stripMargin)

  private def readLine() = StdIn.readLine().trim

}

trait InputReader {
  this: DateParser =>

  def parseStartDate(input: String) = parseDate(input)
  def parseEndDate(input: String) = Option(input).filter(_.trim.length > 0).map(parseDate).getOrElse(new DateTime)
  def parseWeekdayAllowance(input: String, defaultValue: Int): Int = Option(input).filter(_.length > 0).map(_.toInt).getOrElse(defaultValue)
  def parseWeekendAllowance(input: String, defaultValue: Int): Int = Option(input).filter(_.length > 0).map(_.toInt).getOrElse(defaultValue)
}

trait Calculator {
  val startDate: DateTime
  val endDate: DateTime
  val perWeekday: Int
  val perWeekend: Int

  def total = weekdayCount * perWeekday + weekendDayCount * perWeekend
  def totalDays = Days.daysBetween(startDate.toLocalDate, endDate.toLocalDate).getDays + 1
  def weekdayCount = totalDays - weekendDayCount
  def weekendDayCount = (0 until totalDays).count(isWeekend)

  private def isWeekend(index: Int): Boolean = {
    val ddd = startDate.plusDays(index).getDayOfWeek
    ddd == SATURDAY || ddd == SUNDAY
  }

}

trait DateParser {
  def parseDate(str: String): DateTime = {
    try {
      new DateTime(formatter("MM-dd").parse(str)).withYear(new DateTime().getYear)
    } catch {
      case e: ParseException => new DateTime(formatter().parse(str))
    }
  }

  def display(date: DateTime): String = {
    formatter().format(date.toDate)
  }

  private def formatter(pattern: String = "yyyy-MM-dd") = {
    val format = new SimpleDateFormat(pattern)
    format.setLenient(false)
    format
  }

}
