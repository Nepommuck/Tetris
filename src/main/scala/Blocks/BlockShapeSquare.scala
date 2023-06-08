package Blocks

import Utils.{RotateDirection, Vec2d}
import scalafx.scene.paint.Color

class BlockShapeSquare(position: Vec2d) extends PlayingBlock(
    color = Color.Yellow, position, contentAsList = List(
        (1, 0), (0, 1), (0, 0), (1, 1)
    )) {

    override def contentRotated(direction: RotateDirection): List[Vec2d] = content
}
