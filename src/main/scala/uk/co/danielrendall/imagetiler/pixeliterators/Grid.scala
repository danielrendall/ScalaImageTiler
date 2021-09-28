package uk.co.danielrendall.imagetiler.pixeliterators

import uk.co.danielrendall.imagetiler.interfaces.PixelIteratorFactory.BasePixelIterator
import uk.co.danielrendall.imagetiler.interfaces.{PixelIterator, PixelIteratorFactory}
import uk.co.danielrendall.imagetiler.model.Pixel

/**
 * <Insert Description Here>
 *
 */
case object Grid extends PixelIteratorFactory {
  override def getPixelIterator(xMin: Int, width: Int, yMin: Int, height: Int): PixelIterator = {
    var x: Int = xMin
    var y: Int = yMin
    var hasMorePixels: Boolean = true

    new BasePixelIterator(xMin, width, yMin, height) {
      override def hasNext: Boolean = hasMorePixels

      override def next(): Pixel = {
        val next = Pixel(x, y)
        x = x + 1
        if (x > xMax) {
          x = xMin
          y = y + 1
        }
        if (y > yMax) {
          hasMorePixels = false
        }
        next
      }
    }
  }
}
