package org.continuum.vacuum.propertybasedtesting

import Prop._

/**
 * @author nature.song
 */

object Prop {
  type FailedCase = String
  type SuccessCount = Int
  type TestCases = Int
  
//  type Result = Option[(FailedCase, SuccessCount)]
  sealed trait Result {
    def isFalsified: Boolean
  }
  
  case object Passed extends Result {
    def isFalsified = false
  }
  
  case class Falsified(failure: FailedCase, successes: SuccessCount) 
    extends Result {
    def isFalsified = true
  }
  
  case object Proved extends Result {
    def isFalsified = false
  }

  def forAll[A](g: Gen[A])(f: A => Boolean): Prop = Prop {
    (_, n, rng) => randomStream(g)(rng).zip(Stream.from(0)).take(n).map{
      case (gened, idx) => {
        try {
          if(f(gened)) Passed else Falsified(g.toString, idx)
        } catch {
          case e: Exception => Falsified(buildMsg(gened, e), idx)
        }
      } 
    }.find(_.isFalsified).getOrElse(Passed)
  }  

  def forAll[A](sg: SGen[A])(f: A => Boolean): Prop = 
    forAll(sg(_))(f)

  def forAll[A](g: Int => Gen[A])(f: A => Boolean): Prop = Prop {
    (max, n, rng) =>
      val casesPerSize = (n - 1) / max + 1
      val props: Stream[Prop] = 
        Stream.from(0).take( (n min max) + 1).map(i => forAll(g(i))(f))
      val prop: Prop = 
        props.map(p => Prop{ (max, n, rng) =>
          p.run(max, casesPerSize, rng)
        }).toList.reduce(_ && _)
      prop.run(max, n, rng)
  }

  def randomStream[A](g: Gen[A])(rng: RNG): Stream[A] =
    Stream.unfold(rng)(rng => Some(g.sample.run(rng)))
  
  def buildMsg[A](gened: A, e: Exception): String = 
    s"test case: $gened\n" +
    s"generated an exception: ${e.getMessage}\n" +
    s"stack trace:\n ${e.getStackTrace.mkString("\n")}"
    
  def run(p: Prop, maxSize: Int = 100, testCases: Int = 100,
      rng: RNG = RNG.Simple(System.currentTimeMillis)): Unit = {
    p.run(maxSize, testCases, rng) match {
      case Falsified(msg, n) =>
        println(s"! Falsified after $n passed tests:\n $msg")
      case Passed =>
        println(s"+ OK, passed $testCases tests.")
      case Proved =>
        println(s"+ OK, proved property.")
    }
  } 
  
  def check(p: => Boolean): Prop = Prop { (_,_,_) =>
    if(p) Passed else Falsified("()", 0)
  }
}

case class Prop(run: (Int, TestCases, RNG) => Result) {
//  def check(p: => Boolean): Prop {
//    forAll(unit(()))(_ => result)
//  }

  def &&(p: Prop): Prop = Prop {
    (max, n, rng) => run(max, n, rng) match {
      case Passed | Proved => p.run(max, n, rng)
      case x => x
    }
  }
}

object Gen {
  def listOf[A](g: Gen[A]): SGen[List[A]] = 
    SGen(n => g.listOfN(n))

  
  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] = 
    Gen(State.sequence(List.fill(n)(g.sample)))

  def boolean: Gen[Boolean] = 
    Gen(State(RNG.boolean))

  def choose(start: Int, stopExclusive: Int): Gen[Int] = 
    Gen(State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive-start)))

  def unit[A](a: => A): Gen[A] = 
    Gen(State.unit(a))
}

case class Gen[+A](sample: State[RNG, A]) {
  def listOfN(n: Int): Gen[List[A]] = 
    Gen.listOfN(n, this)
}

case class SGen[+A](sg: Int => Gen[A]) {
  def apply(n: Int): Gen[A] = sg(n) 
}
