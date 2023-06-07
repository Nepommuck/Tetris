package Blocks

import Utils.Vec2d
import scalafx.scene.paint.Color

class BlockShapeT(position: Vec2d) extends PlayingBlock(
    color = Color.Purple, position, contentAsList = List(
        (0, 0), (0, 1), (-1, 0), (1, 0)
    )) {
    
}
