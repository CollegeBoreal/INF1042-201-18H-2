// TODO faire cela au plus vite
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  // n!
  def factoriel(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <=0 ) acc
      else go(n-1, n*acc)
    go(n, 1)
  }

  // Type Inference
  private def formatAbs(x: Int) = {
    val msg = "La valeur absolue de %d est %d"
    msg.format(x, abs(x))
  }

  private def formatFactoriel(x: Int) = {
    val msg = "Le factoriel de %d est %d"
    msg.format(x, factoriel(x))
  }

  // Remplacer les deux fonctions ci-dessus en une seul
  // en utilisant HOF
  private def formatResult[A](name: String, x: A, f: A => Boolean) = {
    val msg = "%s de %d est %d"
    msg.format(name, 2, 3)
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(formatFactoriel(7))
  }

}