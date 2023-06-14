import org.scalatest.funsuite.AnyFunSuite

import Blocks.*
import Utils.{Vec2d, MoveDirection}

class PlayingBlockMovementTest extends AnyFunSuite {
    def getAllTestBlocks(initPos: Vec2d): List[PlayingBlock] = List(
        BlockShapeI(initPos), BlockShapeL(initPos), BlockShapeL2(initPos), BlockShapeS(initPos), BlockShapeS2(initPos),
        BlockShapeSquare(initPos), BlockShapeT(initPos),
    )

    test("PlayingBlock move down") {
        val blocks1 = getAllTestBlocks(Vec2d(5, 6))
        val blocks2 = getAllTestBlocks(Vec2d(-10, 20))

        for (block <- blocks1) {
            block.move(Left(MoveDirection.Down))
            assert(block.position === Vec2d(5, 5))
        }
        for (block <- blocks2) {
            block.move(Left(MoveDirection.Down))
            assert(block.position === Vec2d(-10, 19))
        }
    }

    test("PlayingBlock move up") {
        val blocks1 = getAllTestBlocks(Vec2d(5, 6))
        val blocks2 = getAllTestBlocks(Vec2d(-10, 20))

        for (block <- blocks1) {
            block.move(Left(MoveDirection.Up))
            assert(block.position === Vec2d(5, 7))
        }
        for (block <- blocks2) {
            block.move(Left(MoveDirection.Up))
            assert(block.position === Vec2d(-10, 21))
        }
    }

    test("PlayingBlock move left") {
        val blocks1 = getAllTestBlocks(Vec2d(5, 6))
        val blocks2 = getAllTestBlocks(Vec2d(-10, 20))

        for (block <- blocks1) {
            block.move(Left(MoveDirection.Left))
            assert(block.position === Vec2d(4, 6))
        }
        for (block <- blocks2) {
            block.move(Left(MoveDirection.Left))
            assert(block.position === Vec2d(-11, 20))
        }
    }

    test("PlayingBlock move right") {
        val blocks1 = getAllTestBlocks(Vec2d(5, 6))
        val blocks2 = getAllTestBlocks(Vec2d(-10, 20))

        for (block <- blocks1) {
            block.move(Left(MoveDirection.Right))
            assert(block.position === Vec2d(6, 6))
        }
        for (block <- blocks2) {
            block.move(Left(MoveDirection.Right))
            assert(block.position === Vec2d(-9, 20))
        }
    }
}
