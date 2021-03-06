import State._

case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))
  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = for (a <- this; b <- sb) yield f(a,b)
  def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => { val (a, s1) = run(s); f(a).run(s1) })
}

object State {

  def unit[S, A](a: A): State[S, A] = State(s => (a, s))

  //The idiomatic solution is expressed viad foldRight
  def sequenceViaFoldRight[S,A](sas: List[State[S,A]]): State[S, List[A]]=
    sas.foldRight(unit[S,List[A]](List()))((f, acc) => f.map2(acc) (_ :: _))

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = sequenceViaFoldRight(sas)

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))
  } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))
}