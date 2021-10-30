package uk.co.danielrendall.imagetiler.svg

import java.awt.Color
import scala.language.implicitConversions

sealed trait Colour {
  def stringValue: String
}

object Colour {
  implicit def stringCanBeColour(name: String): Colour = StringAsColour(name)

  implicit def colorCanBeColour(color: Color): Colour = ColorAsColour(color)
}

case class StringAsColour(stringValue: String) extends Colour

case class ColorAsColour(color: Color) extends Colour {

  def stringValue: String = {
    val r = color.getRed
    val g = color.getGreen
    val b = color.getBlue
    val rh = Integer.toHexString(r)
    val gh = Integer.toHexString(g)
    val bh = Integer.toHexString(b)
    "#" + (if (r < 16) "0"
    else "") + rh + (if (g < 16) "0"
    else "") + gh + (if (b < 16) "0"
    else "") + bh
  }

}
