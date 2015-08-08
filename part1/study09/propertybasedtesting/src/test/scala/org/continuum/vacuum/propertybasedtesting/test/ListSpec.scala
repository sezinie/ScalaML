package org.continuum.vacuum.propertybasedtesting.test

import org.scalacheck._
import Prop.forAll

/**
 * @author nature.song
 */
object ListSpec extends Properties("List Spec"){
  property("double reverse") = forAll {
    (x: List[Int]) => x.reverse.reverse == x
  }
  
//  property("zip reverse") = forAll {
//    (x: List[Int], y: List[Int]) => 
//      (x.reverse zip y.reverse) == (x zip y).reverse 
//  }
  
  property("zip reverse") = forAll {
    (x: List[Int], y: List[Int]) => {
      val x1 = x.take(y.length)
      val y1 = y.take(x.length)
      
      (x1.reverse zip y1.reverse) == (x1 zip y1).reverse
    } 
  }

//  property("map + and size") = forAll {
//    (m: Map[Int, Int], key: Int, value: Int) =>
//      (m + (key->value)).size == (m.size + 1)
//  }
}