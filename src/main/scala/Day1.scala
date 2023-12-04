

object Day1 extends App with AdventUtils {
  val coordinates: List[String] = getDataOfDay(this.getClass.getSimpleName.replace("$", ""))

  val getFirstAndLastNumber: List[String] = coordinates.map(c => {
    val digits: String = c.filter(_.isDigit)
    digits.head.toString + digits.last.toString
  })

  val result: Int = getFirstAndLastNumber.map(_.toInt).reduce(_ + _)

  println(result)
}
