import scala.util.Try

//case class SetGame(reds: Map[String, Int], blues: Map[String, Int], greens: Map[String, Int])
case class SetGame(reds: Int, blues: Int, greens: Int)

object Day2 extends App with AdventUtils {
  val games: List[String] =  getDataOfDay(this.getClass.getSimpleName.replace("$", ""))

  val numRedCubes: Int = 12
  val numGreenCubes: Int = 13
  val numBlueCubes: Int = 14

  def getGameId(head: String): String = head.split(":").toList(0).split("Game ").toList.last
  def trimGameHead(gameLine: String): String = gameLine.split(": ").toList.tail(0)

  def processIndividualSet(setGame: List[String]): SetGame = {
    SetGame(getReds(setGame), getBlues(setGame), getGreens(setGame))
  }


  //TODO Fusionar los tres métodos
  def getReds(setGame: List[String]): Int = {
    val numReds: Int = Try(setGame.filter(_.contains("red"))(0).filter(_.isDigit).toInt).getOrElse(0)
    numReds
  }

  def getBlues(setGame: List[String]): Int = {
    val numBlues: Int = Try(setGame.filter(_.contains("blue"))(0).filter(_.isDigit).toInt).getOrElse(0)
    numBlues
  }

  def getGreens(setGame: List[String]): Int = {
    val numGreens: Int = Try(setGame.filter(_.contains("green"))(0).filter(_.isDigit).toInt).getOrElse(0)
    numGreens
  }


  def allSetsAreValid(listOfSets: List[SetGame]): Boolean = {
    println("en allSetsAreValid con " + listOfSets)

    val mapWithValids: List[Boolean] = listOfSets.map(s => redsValid(s) && bluesValid(s) && greensValid(s))

    mapWithValids.foreach(println)

    println("ergo results is :")
    println(mapWithValids.forall(_ == true))

    mapWithValids.forall(_ == true)
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


  allSetsFromGame.foreach(println)


  println("===========================================================================================================")
  println("===========================================================================================================")


  val validGames = allSetsFromGame.map(set => allSetsAreValid(set.values.last))

  //validGames.foreach(println)



}
