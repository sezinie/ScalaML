package org.continuum.vacuum.propertybasedtesting.test

import org.scalacheck._
import Prop.forAll

/**
 * @author nature.song
 */
object StringSpec extends Properties("String Spec"){
  property("startsWith") = forAll {
    (x: String, y: String) => (x+y).startsWith(x)
  }
  
  property("concatenation length") = forAll {
    (x: String, y: String) => x.length <= (x+y).length
  }
}

