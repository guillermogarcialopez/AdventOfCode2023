import scala.io.Source

trait AdventUtils {
  val baseDataPath: String = "C:\\Users\\guill\\Desktop\\git_projects\\AdventOfCode2023\\data\\"
  def conformDataPath(className: String): String = baseDataPath + className + "\\" + className.toLowerCase + ".txt"
  def getDataOfDay(className: String): List[String] = Source.fromFile(conformDataPath(className)).getLines.toList
}
