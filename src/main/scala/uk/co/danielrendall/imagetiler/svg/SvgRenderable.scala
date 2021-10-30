package uk.co.danielrendall.imagetiler.svg

import org.w3c.dom.Element
import uk.co.danielrendall.mathlib.geom2d.shapes.{Polygon, Shape}

trait SvgRenderable[T <: Shape[T]] {
  def getElement(shape: T, elementFactory: String => Element): Element
}

object SvgRenderable {

  implicit object PolygonSvgRenderable extends SvgRenderable[Polygon] {
    override def getElement(shape: Polygon,
                            elementFactory: String => Element): Element = {
      val pathEl = elementFactory("path")
      val lineString = shape.points.map(p => s"${p.x} ${p.y} ").mkString("M ", "L ", "z")
      pathEl.setAttributeNS(null, "d", lineString)
      pathEl
    }
  }

}
