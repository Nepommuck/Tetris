package Blocks

import Utils.Vec2d
import scalafx.scene.paint.Color

class BlockShapeL(position: Vec2d) extends PlayingBlock(
    color = Color.Orange, position, contentAsList = List(
        (0, 0), (1, 1), (-1, 0), (1, 0)
    )) {
    
}
