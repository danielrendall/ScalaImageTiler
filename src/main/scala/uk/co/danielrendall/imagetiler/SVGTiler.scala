package uk.co.danielrendall.imagetiler

import org.apache.batik.anim.dom.SVGDOMImplementation
import org.w3c.dom.svg.SVGDocument
import org.w3c.dom.{Document, Element}
import uk.co.danielrendall.imagetiler.interfaces._
import uk.co.danielrendall.imagetiler.model.TileContext

import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Use once...
 */
private class SVGTiler(scale: Double,
                       input: BufferedImage,
                       pixelIteratorFactory: PixelIteratorFactory,
                       pixelFilter: PixelFilter,
                       svgTile: TileFactory) {

  lazy private val domImpl: SVGDOMImplementation =
    SVGDOMImplementation.getDOMImplementation.asInstanceOf[SVGDOMImplementation]


  lazy private val width: Int = input.getWidth
  lazy private val height: Int = input.getHeight

  lazy private val halfWidth = width.toDouble / 2.0
  lazy private val halfHeight = height.toDouble / 2.0

  lazy private val svgWidth: Double = width.toDouble * scale
  lazy private val svgHeight: Double = height.toDouble * scale


  def go(): SVGDocument = {
    val pixelIterator: PixelIterator =
      pixelIteratorFactory.getPixelIterator(0, width, 0, height)

    // This is all in one thread; we can re-use this array
    val arr = new Array[Int](4)
    val raster = input.getRaster

    val drawOps: Iterator[DrawOperation] = pixelIterator.flatMap { p =>
      raster.getPixel(p.x, p.y, arr)
      val color = new Color(arr(0), arr(1), arr(2))
      if (pixelFilter.shouldInclude(p, color)) {

        val left = scale * (p.x.toDouble - halfWidth)
        val top = scale * (p.y.toDouble - halfHeight)
        val right = left + scale
        val bottom = top + scale

        svgTile.getTile(TileContext(left, right, top, bottom, color))
      } else None
    }

    val document: SVGDocument =
      domImpl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null).asInstanceOf[SVGDocument]

    val root = document.getDocumentElement
    root.setAttributeNS(null, "width", "" + svgWidth)
    root.setAttributeNS(null, "height", "" + svgHeight)

    val outerGroup = createElement(document, "g")

    drawOps.foreach { drawOp =>
      val group = createElement(document, "g")
      drawOp(group)
      outerGroup.appendChild(group)
    }

    outerGroup.setAttributeNS(null, "transform", "translate(" + (svgWidth / 2.0.toDouble) + "," + (svgHeight / 2.0.toDouble) + ")")
    root.appendChild(outerGroup)

    document
  }

  private def createElement(document: Document, name: String): Element =
    document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, name)


}


object SVGTiler {

  private val DEFAULT_SCALE: Double = 100.0d

  def apply(input: BufferedImage,
            pixelIteratorFactory: PixelIteratorFactory,
            pixelFilter: PixelFilter,
            svgTile: TileFactory): SVGDocument = {
    new SVGTiler(DEFAULT_SCALE, input, pixelIteratorFactory, pixelFilter, svgTile).go()

  }


}
