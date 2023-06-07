package Blocks

import Utils.Vec2d
import scalafx.scene.paint.Color

class BlockShapeI(position: Vec2d) extends PlayingBlock(
    color = Color.Cyan, position, contentAsList = List(
        (0, 0), (-1, 0), (1, 0), (2,0)
    )) {
    
}
