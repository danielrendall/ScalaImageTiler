package uk.co.danielrendall.imagetiler.svg

import org.w3c.dom.Element
import uk.co.danielrendall.imagetiler.model.TileContext
import uk.co.danielrendall.mathlib.geom2d.shapes.Shape

object Implicits {
  implicit class SvgElementOps(element: Element) {

    def appendShape[T <: Shape[T]](shape: T)(implicit svgRenderable: SvgRenderable[T], tileContext: TileContext): Unit = {
      element.appendChild(svgRenderable.getElement(shape, element.getOwnerDocument.createElement))
    }

  }
}
