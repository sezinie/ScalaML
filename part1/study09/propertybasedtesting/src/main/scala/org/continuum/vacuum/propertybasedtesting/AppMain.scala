package org.continuum.vacuum.propertybasedtesting

import Gen._
import Prop._

object AppMain {

  def main(args: Array[String]): Unit = {
    println("Welcome to Property Based Testing")

    val smallInt = Gen.choose(-10, 10)

    println("smallInt: " + smallInt)

    val maxProp = forAll(listOf(smallInt)) { ns =>
      val max = ns.max
      !ns.exists(_ > max)
    }

    val intList = Gen.listOf(Gen.choose(0, 100))

    val prop =
      forAll(intList)(ns => ns.reverse.reverse == ns) &&
        forAll(intList)(ns => ns.headOption == ns.reverse.lastOption)
    Prop.run(prop)

    val failingProp = forAll(intList)(ns => ns.reverse == ns)
    Prop.run(failingProp)
  }

}