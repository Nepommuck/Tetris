package Blocks

import Utils.{MoveDirection, RotateDirection, Vec2d}
import scalafx.scene.paint.Color

abstract class PlayingBlock(color: Color, var position: Vec2d, contentAsList: List[(Int, Int)])
  extends Block(color) {
    var content: List[Vec2d] = contentAsList.map((x, y) => Vec2d(x, y))

    def move(direction: Either[MoveDirection, RotateDirection]): Unit = {
        direction match
            case Left(moveDirection) =>
                position += moveDirection.toVec2d

            case Right(rotateDirection) =>
                content = contentRotated(rotateDirection)
    }

    def contentRotated(direction: RotateDirection): List[Vec2d] =
        content.map(v => v.rotated(direction))

    def contentPositions: List[Vec2d] =
        content.map(v => v + position)
}
