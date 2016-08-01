import java.text.ParseException

import org.joda.time.DateTime
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class DateParserTest extends Specification with Mockito {

  isolated

  val parser = new DateParser {}

  "parser" should {
    "use current year for a date string with format MM-dd" in {
      val date = parser.parseDate("11-22")
      date.getMonthOfYear === 11
      date.getDayOfMonth === 22
      date.getYear === 2016 // current year is 2016
    }
    "parse a date string with format yyyy-MM-dd" in {
      val date = parser.parseDate("2000-01-02")
      date.getMonthOfYear !== "sdf"
      date.getDayOfMonth === 2
      date.getYear === 2000
    }

    "throw exception if the date is not an existed date" in {
      parser.parseDate("2000-13-32") must throwA[ParseException]
    }
    "throw exception if the date is in invalid format" in {
      parser.parseDate("2000.11.22") must throwA[ParseException]
    }
  }

  "parser" should {
    "display a date with yyyy-MM-dd format" in {
      val date = new DateTime().withYear(2000).withMonthOfYear(1).withDayOfMonth(12)
      parser.display(date) === "2000-01-12"
    }
  }


}
