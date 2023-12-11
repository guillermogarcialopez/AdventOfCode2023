import scala.util.Try

//case class SetGame(reds: Map[String, Int], blues: Map[String, Int], greens: Map[String, Int])
case class SetGame(reds: Int, blues: Int, greens: Int)

object Day2 extends App with AdventUtils {
  val games: List[String] =  getDataOfDay(this.getClass.getSimpleName.replace("$", ""))

  /* Part one */
  val numRedCubes: Int = 12
  val numGreenCubes: Int = 13
  val numBlueCubes: Int = 14

  def getGameId(head: String): String = head.split(":").toList.head.split("Game ").toList.last
  def trimGameHead(gameLine: String): String = gameLine.split(": ").toList.tail.head

  def processIndividualSet(setGame: List[String]): SetGame = {
    SetGame(getColours("red", setGame), getColours("blue", setGame), getColours("green", setGame))
  }
  def getColours(colour: String, setGame: List[String]): Int = {
    Try(setGame.filter(_.contains(colour)).head.filter(_.isDigit).toInt).getOrElse(0)
  }

  def redsValid(set: SetGame): Boolean = set.reds <= numRedCubes
  def bluesValid(set: SetGame): Boolean = set.blues <= numBlueCubes
  def greensValid(set: SetGame): Boolean = set.greens <= numGreenCubes

  val allSetsFromGame = games.map(g => {
    val gameHead: String = getGameId(g)
    val setsFromGame: List[SetGame] = trimGameHead(g).split("; ").toList.map(_.split(", ").toList)
      .map(processIndividualSet)
    Map(gameHead -> setsFromGame)
  })

  def allSetsAreValid(game: Map[String, List[SetGame]]): Map[String, Boolean] = {
    val listOfSets: List[SetGame] = game.values.last
    val mapWithValids: List[Boolean] = listOfSets.map(s => redsValid(s) && bluesValid(s) && greensValid(s))
    Map(game.keys.last -> mapWithValids.forall(_ == true))
  }

  val validGames = allSetsFromGame.map(allSetsAreValid)

  println(validGames.filter(_.values.last).map(_.keySet.head).map(_.toInt).sum)
}
