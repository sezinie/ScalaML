package org.continuum.vacuum.propertybasedtesting.test

import org.scalacheck._
import Prop.forAll

import org.continuum.vacuum.propertybasedtesting._

/**
 * @author nature.song
 */
object TreeSpec extends Properties("Tree Spec"){
  val ints = Gen.choose(-100, 100)
  
  def leafs: Gen[Leaf] = 
    for {
      value <- ints
    } yield Leaf(value)

  def nodes: Gen[Node] = 
    for {
      left <- trees
      right <- trees
    } yield Node(left, right)

  def trees: Gen[Tree] = Gen.oneOf(leafs, nodes)
}