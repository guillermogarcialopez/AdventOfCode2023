import scala.io.Source

object Day1 extends App {
  val coordinates: List[String] = Source.fromFile("C:\\Users\\guill\\Desktop\\git_projects\\AdventOfCode2023\\data\\Day1\\day1.txt").getLines.toList

  val getFirstAndLastNumber: List[String] = coordinates.map(c => {
    val digits: String = c.filter(_.isDigit)
    digits.head.toString + digits.last.toString
  })

  val result: Int = getFirstAndLastNumber.map(_.toInt).reduce(_ + _)
  println(result)
}
