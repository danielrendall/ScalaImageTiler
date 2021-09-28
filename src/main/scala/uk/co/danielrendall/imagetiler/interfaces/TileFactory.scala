package uk.co.danielrendall.imagetiler.interfaces

import uk.co.danielrendall.imagetiler.model.TileContext

trait TileFactory {

  def getTile(context: TileContext): Option[DrawOperation]

}
