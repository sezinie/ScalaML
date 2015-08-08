package org.continuum.vacuum.propertybasedtesting.test

import org.scalacheck._
import org.scalacheck.Prop.forAll
import org.continuum.vacuum.propertybasedtesting._

/**
 * @author nature.song
 */
object VectorSpec extends Properties("Vector Spec"){
  val doubles = Arbitrary.arbitrary[Double]
  val booleans = Gen.oneOf(true, false)
  val twodigits = Gen.choose(-100, 100)
  val ints = twodigits

  property("sqrt") = forAll(ints) {
    d => math.sqrt(d*d) == math.abs(d)
  }

  val vectors: Gen[Vector] = for {
    x <- Gen.choose(0, 100)
    y <- Gen.choose(0, 100)
  } yield Vector(x,y)

  property("* and length") = forAll(vectors, Gen.choose(0, 100)) {
    (v: Vector, s: Int) => {
      if(s >= 1.0) (v*s).length >= v.length
      else (v*s).length < v.length
    }
  }
  
}