package org.continuum.vacuum.propertybasedtesting

/**
 * @author nature.song
 */
case class Vector(x: Int, y: Int) {
  def *(s: Int) = Vector(x*s, y*s)
  def length: Double = Math.sqrt(x*x + y*y)
}
