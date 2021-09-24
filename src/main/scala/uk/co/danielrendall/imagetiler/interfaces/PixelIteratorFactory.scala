package uk.co.danielrendall.imagetiler.interfaces

trait PixelIteratorFactory {

  def getPixelIterator(xMin: Int, width: Int, yMin: Int, height: Int): PixelIterator

}
