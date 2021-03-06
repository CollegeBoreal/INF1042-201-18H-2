// TODO faire cela au plus vite
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  // n!
  def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)

    go(n, 1)
  }

  // Type Inference
  private def formatAbs(x: Int) = {
    val msg = "La valeur absolue de %d est %d"
    msg.format(x, abs(x))
  }

  private def formatFactorial(x: Int) = {
    val msg = "Le factoriel de %d est %d"
    msg.format(x, factorial (x))
  }

  // Remplacer les deux fonctions ci-dessus en une seule
  // en utilisant HOF
  private def formatResult(name: String, x: Int, f: Int => Int) = {
    val msg = "La valeur absolue %s de %d est %d"
    msg.format(name, x, f(x))
  }


  def main(args: Array[String]): Unit = {
    //    println(formatAbs(-42))
//        println(formatFactorial(7))
    println(formatResult("La valeur absolue", -42, abs))
    println(formatResult("Le factoriel", 7, factorial))
  }

}
