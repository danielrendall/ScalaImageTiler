package uk.co.danielrendall.imagetiler.tiles

import uk.co.danielrendall.imagetiler.interfaces.{DrawOperation, TileFactory}
import uk.co.danielrendall.imagetiler.model.TileContext
import uk.co.danielrendall.imagetiler.svg.Implicits.SvgElementOps
import uk.co.danielrendall.mathlib.geom2d.shapes.Polygon
import uk.co.danielrendall.mathlib.geom2d.shapes.Polygon.{SideLength, VerticalSideCrossesPositiveXAxis}

case class Simple(inset: Double,
                  strokeWidth: Double,
                  darkOpacity: Double,
                  lightOpacity: Double) extends TileFactory {

  override def getTile(implicit context: TileContext): Option[DrawOperation] = {
    Option { group =>
      group.appendShape(Polygon(4, context.center, SideLength(context.edge), VerticalSideCrossesPositiveXAxis))
    }
  }
}

object Simple {
  def apply(): Simple = Simple(0.15d, 0.05d, 0.8d, 0.6d)
}
