package uk.co.danielrendall.imagetiler

import org.w3c.dom.Element
import uk.co.danielrendall.imagetiler.model.Pixel

package object interfaces {

  type DrawOperation = Element => Unit

  type PixelIterator = Iterator[Pixel]
}
