package org.continuum.vacuum.propertybasedtesting

/**
 * @author nature.song
 */

trait Tree
case class Node(left: Tree, right: Tree) extends Tree
case class Leaf(value: Int) extends Tree
