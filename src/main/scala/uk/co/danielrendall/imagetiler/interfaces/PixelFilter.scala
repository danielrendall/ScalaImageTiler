package uk.co.danielrendall.imagetiler.interfaces

import uk.co.danielrendall.imagetiler.model.Pixel

import java.awt.Color

trait PixelFilter {

  def shouldInclude(p: Pixel, c: Color): Boolean

}

object PixelFilter {

  val yesToAll: PixelFilter = (p: Pixel, c: Color) => true

}
