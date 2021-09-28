package uk.co.danielrendall.imagetiler.interfaces

import uk.co.danielrendall.mathlib.geom2d.Point

trait PixelIteratorFactory {

  def getPixelIterator(xMin: Int, width: Int, yMin: Int, height: Int): PixelIterator

}


object PixelIteratorFactory {

  abstract class BasePixelIterator(xMin: Int, width: Int, yMin: Int, height: Int) extends PixelIterator {
    lazy val xMax: Int = xMin + width - 1
    lazy val yMax: Int = yMin + height - 1
    lazy val center: Point = Point(xMin.toDouble + width.toDouble / 2.0, yMin.toDouble + height / 2.0)
  }

}
