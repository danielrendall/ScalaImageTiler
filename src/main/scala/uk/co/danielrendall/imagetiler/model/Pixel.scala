package uk.co.danielrendall.imagetiler.model

import uk.co.danielrendall.mathlib.geom2d.Point

case class Pixel(x: Int, y: Int) {
  lazy val center: Point = Point(0.5d + x.toDouble, 0.5d + y.toDouble)
}

object Pixel {

}
