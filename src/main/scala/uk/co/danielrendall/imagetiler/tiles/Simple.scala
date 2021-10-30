package uk.co.danielrendall.imagetiler.tiles

import uk.co.danielrendall.imagetiler.interfaces.{Customiser, DrawOperation, TileFactory}
import uk.co.danielrendall.imagetiler.model.TileContext
import uk.co.danielrendall.imagetiler.svg.Implicits.SvgElementOps
import uk.co.danielrendall.mathlib.geom2d.shapes.Polygon
import uk.co.danielrendall.mathlib.geom2d.shapes.Polygon.{SideLength, VerticalSideCrossesPositiveXAxis}

case class Simple(inset: Double,
                  strokeWidth: Double,
                  darkOpacity: Double,
                  lightOpacity: Double) extends TileFactory {

  import Customiser._

  private val blackStroke = stroke("black")

  override def getTile(implicit context: TileContext): Option[DrawOperation] = {

    val customiser = chain(
      blackStroke,
      fill(context.color)
    )

    Option { group =>
      group.appendShape(Polygon(4, context.center, SideLength(context.edge * (1.0 - 2 * inset)),
        VerticalSideCrossesPositiveXAxis), customiser)
    }
  }
}

object Simple {
  def apply(): Simple = Simple(0.15d, 0.05d, 0.8d, 0.6d)
}
