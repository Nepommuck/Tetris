package Blocks

import Utils.Vec2d

class BlockShapeT(color: String, position: Vec2d) extends PlayingBlock(
    color, position, contentAsList = List(
        (0, 0), (0, 1), (-1, 0), (1, 0)
    )) {
    
}
