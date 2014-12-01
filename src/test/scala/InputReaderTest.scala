import org.joda.time.DateTime
import org.specs2.mutable.Specification

class InputReaderTest extends Specification {

  isolated

  val reader = new InputReader with DateParser {}

  "InputReader" should {
    "parse a string to a start date" in {
      val date = reader.parseStartDate("2012-01-02")
      date.getYear === 2012
      date.getMonthOfYear === 1
      date.getDayOfMonth === 2
    }

    "parse a string to a end date" in {
      val date = reader.parseEndDate("2012-01-02")
      date.getYear === 2012
      date.getMonthOfYear === 1
      date.getDayOfMonth === 2
    }

    "parse an empty string to current date" in {
      val date = reader.parseEndDate("")
      val now = new DateTime()
      date.getYear === now.getYear
      date.getMonthOfYear === now.getMonthOfYear
      date.getDayOfMonth === now.getDayOfMonth
    }

    "parse weekday allowance" in {
      reader.parseWeekdayAllowance("23", 44) === 23
    }

    "use default value as weekday allowance if input is empty" in {
      reader.parseWeekdayAllowance("", 44) === 44
    }

    "parse weekend allowance" in {
      reader.parseWeekendAllowance("23", 44) === 23
    }

    "use default value as weekend allowance if input is empty" in {
      reader.parseWeekendAllowance("", 44) === 44
    }
  }

}
