package uk.co.danielrendall.imagetiler.svg

import org.w3c.dom.Element
import uk.co.danielrendall.imagetiler.interfaces.Customiser
import uk.co.danielrendall.mathlib.geom2d.shapes.Shape

object Implicits {

  val SVG_NS: String = "http://www.w3.org/2000/svg"

  implicit class SvgElementOps(element: Element) {

    def appendShape[T <: Shape[T]](shape: T, customiser: Customiser = identity)
                                  (implicit svgRenderable: SvgRenderable[T]): Unit = {
      element.appendChild(customiser(svgRenderable.getElement(shape, element.getOwnerDocument.createElementNS(SVG_NS, _))))
    }

  }
}
