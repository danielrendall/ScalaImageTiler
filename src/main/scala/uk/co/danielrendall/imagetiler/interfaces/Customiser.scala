package uk.co.danielrendall.imagetiler.interfaces

import uk.co.danielrendall.imagetiler.svg.Colour

object Customiser {

  def stroke(colour: Colour, width: Double = 1.0): Customiser = el => {
    el.setAttributeNS(null, "stroke", colour.stringValue)
    el.setAttributeNS(null, "stroke-width", width.toString)
    el
  }

  def fill(colour: Colour, fillOpacity: Double = 1.0): Customiser = el => {
    el.setAttributeNS(null, "fill", colour.stringValue)
    el.setAttributeNS(null, "fill-opacity", fillOpacity.toString)
    el
  }

  def chain(customisers: Customiser*): Customiser = el => {
    customisers.foldLeft(el) { case (el, next) => next(el) }
  }


}
