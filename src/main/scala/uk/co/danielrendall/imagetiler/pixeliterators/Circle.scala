package uk.co.danielrendall.imagetiler.pixeliterators

import uk.co.danielrendall.imagetiler.interfaces.PixelIteratorFactory.BasePixelIterator
import uk.co.danielrendall.imagetiler.interfaces.{PixelIterator, PixelIteratorFactory}
import uk.co.danielrendall.imagetiler.model.Pixel
import uk.co.danielrendall.mathlib.util.epsilon.Default

import scala.collection.mutable

case class Circle(invert: Boolean) extends PixelIteratorFactory {
  override def getPixelIterator(xMin: Int, width: Int, yMin: Int, height: Int): PixelIterator = {

    new BasePixelIterator(xMin, width, yMin, height) {

      implicit object RadiusComparator extends Ordering[Pixel] {
        val distanceCache: mutable.Map[Pixel, Double] = mutable.HashMap[Pixel, Double]()
        val angleCache: mutable.Map[Pixel, Double] = mutable.HashMap[Pixel, Double]()

        override def compare(p1: Pixel, p2: Pixel): Int = {
          val ret = java.lang.Double.compare(
            distanceCache.getOrElseUpdate(p1, distanceToCenter(p1)),
            distanceCache.getOrElseUpdate(p2, distanceToCenter(p2)))
          if (ret == 1) {
            java.lang.Double.compare(
              angleCache.getOrElseUpdate(p1, angleToCenter(p1)),
              angleCache.getOrElseUpdate(p2, angleToCenter(p2)))
          } else ret
        }

        @inline
        private def distanceToCenter(pixel: Pixel): Double = pixel.center.line(center).length

        @inline
        private def angleToCenter(pixel: Pixel): Double = pixel.center.line(center).vec.angle
      }

      val allPixels: Iterator[Pixel] = {
        val set = new mutable.TreeSet[Pixel]()
        Grid.getPixelIterator(xMin, width, yMin, height).foreach(set.add)
        set.iterator
      }

      override def hasNext: Boolean = allPixels.hasNext

      override def next(): Pixel = allPixels.next()



    }


  }
}
