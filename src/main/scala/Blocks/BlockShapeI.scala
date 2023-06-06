package Blocks

import Utils.Vec2d
import scalafx.scene.paint.Color

class BlockShapeI(color: Color, position: Vec2d) extends PlayingBlock(
    color, position, contentAsList = List(
        (0, 0), (-1, 0), (1, 0), (2,0)
    )) {
    
}