package uk.co.danielrendall.imagetiler.interfaces

import uk.co.danielrendall.imagetiler.model.Pixel

abstract class PixelIterator(xMin: Int, width: Int, yMin: Int, height: Int) extends Iterator[Pixel]
