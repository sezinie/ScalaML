package org.continuum.vacuum.propertybasedtesting

object PropertyBasedTesting {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(124); 
  println("Welcome to the Scala worksheet")}
  
  /*
  val intList = Gen.listOf(Gen.choose(0, 100))

  val prop =
  	forAll(intList)(ns => ns.reverse.reverse == ns) &&
  	forAll(intList)(ns => ns.headOption == ns.reverse.lastOption)
  
  val failingProp = forAll(intList)(ns => ns.reverse == ns)
  */

  /*
  scala> prop.check
  + OK, passed 100 tests.
  
  
  scala> failingProp.check
  ! Falsified after 6 passed tests.
  >ARG_0: List(0, 1)
  
  */
  
}
