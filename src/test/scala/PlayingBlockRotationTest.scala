import org.scalatest.funsuite.AnyFunSuite

import Blocks.*
import Utils.Vec2d
import Utils.RotateDirection.*


class PlayingBlockRotationTest extends AnyFunSuite:
    val v0: Vec2d = Vec2d(0, 0)

    test("BlockShapeI rotation") {
        val block: PlayingBlock = BlockShapeI(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (0,1), (0,-1), (0,-2))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (0,-1), (0,1), (0,2))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (1,0), (-1,0), (-2,0))
        )
    }

    test("BlockShapeL rotation") {
        val block: PlayingBlock = BlockShapeL(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (1,-1), (0,1), (0,-1))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (-1,1), (0,-1), (0,1))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (-1,-1), (1,0), (-1,0))
        )
    }

    test("BlockShapeL2 rotation") {
        val block: PlayingBlock = BlockShapeL2(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (1,1), (0,1), (0,-1))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (-1,-1), (0,-1), (0,1))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (1,-1), (1,0), (-1,0))
        )
    }

    test("BlockShapeS rotation") {
        val block: PlayingBlock = BlockShapeS(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (1,1), (0,1), (-1,0))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (-1,-1), (0,-1), (1,0))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (1,-1), (1,0), (0,1))
        )
    }

    test("BlockShapeS2 rotation") {
        val block: PlayingBlock = BlockShapeS2(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (1,0), (0,1), (-1,1))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (-1,0), (0,-1), (1,-1))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (0,-1), (1,0), (1,1))
        )
    }

    test("BlockShapeSquare rotation") {
        val block: PlayingBlock = BlockShapeSquare(v0)
        val initialContent = block.content

        // 1x Right
        assert(
            block.contentRotated(RotateRight) === initialContent
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content === initialContent
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft) === initialContent
        )
    }

    test("BlockShapeT rotation") {
        val block: PlayingBlock = BlockShapeT(v0)

        // 1x Right
        assert(
            block.contentRotated(RotateRight).map(v => v.toTuple) ===
              List((0,0), (1,0), (0,1), (0,-1))
        )
        // 3x Right
        block.content = block.contentRotated(RotateLeft)
        assert(
            block.content.map(v => v.toTuple) ===
              List((0,0), (-1,0), (0,-1), (0,1))
        )
        // 2x Right
        assert(
            block.contentRotated(RotateLeft).map(v => v.toTuple) ===
              List((0,0), (0,-1), (1,0), (-1,0))
        )
    }
