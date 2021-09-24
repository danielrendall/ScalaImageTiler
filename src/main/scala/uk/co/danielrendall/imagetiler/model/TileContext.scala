package uk.co.danielrendall.imagetiler.model

import uk.co.danielrendall.mathlib.geom2d.{Point, Vec}
import uk.co.danielrendall.mathlib.util.epsilon.Default
import uk.co.danielrendall.mathlib.util.Mathlib.mean

import java.awt.Color

case class TileContext(left: Double, right: Double, top: Double, bottom: Double, color: Color) {

  lazy val center: Point = Point(mean(left, right), mean(top, bottom))

  lazy val vector: Vec = Vec(center)

  lazy val distance: Double = vector.length

  lazy val angle: Double = vector.angle

}
