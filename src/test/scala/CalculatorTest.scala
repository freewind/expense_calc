import org.joda.time.DateTime
import org.specs2.mutable.Specification

class CalculatorTest extends Specification {

  isolated

  "total days" should {
    "be calculated correctly if the start date and end date are same" in {
      val calc = calculator(theStartDate = createDate(2014, 12, 28), theEndDate = createDate(2014, 12, 28))
      calc.totalDays === 1
    }
    "be calculated correctly if the start date and end date are not same but in same month" in {
      val calc = calculator(theStartDate = createDate(2014, 12, 10), theEndDate = createDate(2014, 12, 20))
      calc.totalDays === 11
    }
    "be calculated correctly if the start date and end date are in different months" in {
      val calc = calculator(theStartDate = createDate(2014, 11, 30), theEndDate = createDate(2014, 12, 1))
      calc.totalDays === 2
    }
    "be calculated correctly if the start date and end date are in different years" in {
      val calc = calculator(theStartDate = createDate(2014, 12, 31), theEndDate = createDate(2015, 1, 1))
      calc.totalDays === 2
    }
  }

  "weekday and weekend count" should {
    "be calculated correctly if the start date and end date are same and is a weekend" in {
      // 2014, 11, 30 is sunday
      val calc = calculator(theStartDate = createDate(2014, 11, 30), theEndDate = createDate(2014, 11, 30))
      calc.weekdayCount === 0
      calc.weekendDayCount === 1
    }
    "be calculated correctly if the start date and end date are not same but both are weekend" in {
      // 2014-11-29/30 is sat/sun
      val calc = calculator(theStartDate = createDate(2014, 11, 29), theEndDate = createDate(2014, 11, 30))
      calc.weekdayCount === 0
      calc.weekendDayCount === 2
    }
    "be calculated correctly if the start date and end date are same and is a weekday" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2014, 12, 1))
      calc.weekdayCount === 1
      calc.weekendDayCount === 0
    }
    "be calculated correctly if the start date and end date are not same and all are weekday" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2014, 12, 4))
      calc.weekdayCount === 4
      calc.weekendDayCount === 0
    }
    "be calculated correctly if the start date and end date contain weekday and only one weekend day" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2014, 12, 6))
      calc.weekdayCount === 5
      calc.weekendDayCount === 1
    }
    "be calculated correctly if the start date and end date contain weekday and two weekend day" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2014, 12, 7))
      calc.weekdayCount === 5
      calc.weekendDayCount === 2
    }
    "be calculated correctly if the start date and end date in different month and year" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2015, 1, 7))
      calc.weekdayCount === 28
      calc.weekendDayCount === 10
    }
  }

  "total" should {
    "be calculated correctly for a start date and end date in different month and year" in {
      // 2014-12-01 is mon.
      val calc = calculator(theStartDate = createDate(2014, 12, 1), theEndDate = createDate(2015, 1, 7))
      calc.total === 28 * 3 + 10 * 5
    }
  }


  def createDate(year: Int, month: Int, day: Int) = new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(day)

  def calculator(theStartDate: DateTime = createDate(2014, 12, 28),
                 theEndDate: DateTime = createDate(2014, 12, 29),
                 thePerWeekday: Int = 3,
                 thePerWeekend: Int = 5) = new Calculator {
    override val startDate = theStartDate
    override val endDate = theEndDate
    override val weekdayAllowance = thePerWeekday
    override val weekendAllowance = thePerWeekend
  }

}
