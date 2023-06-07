package Blocks

import Utils.Vec2d
import scalafx.scene.paint.Color

class BlockShapeSquare(position: Vec2d) extends PlayingBlock(
    color = Color.Yellow, position, contentAsList = List(
        (1, 0), (0, 1), (0, 0), (1, 1)
    )) {
    
}
