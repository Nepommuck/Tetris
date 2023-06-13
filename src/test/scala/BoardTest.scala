import Blocks.{BlockShapeI, BlockShapeL, BlockShapeL2, BlockShapeS, BlockShapeS2, BlockShapeSquare, BlockShapeT}
import Utils.{RotateDirection, Vec2d}
import org.scalatest.funsuite.AnyFunSuite
class BoardTest extends AnyFunSuite {
   test ("placing blocks test") {
    val board = new Board(10, 8, null)
    assert(board.toString ==
        "=======================\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "=======================\n"
    )

    board.place(new BlockShapeT(new Vec2d(2, 3)))
    assert(board.toString ==
      "=======================\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|     X               |\n" +
        "|   X X X             |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "=======================\n"
    )

    board.place(new BlockShapeL(new Vec2d(5, 3)))
    assert(board.toString ==
      "=======================\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|     X       X       |\n" +
        "|   X X X X X X       |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "=======================\n"
    )


    board.place(new BlockShapeSquare(new Vec2d(4, 5)))
    assert(board.toString ==
      "=======================\n" +
        "|                     |\n" +
        "|         X X         |\n" +
        "|         X X         |\n" +
        "|     X       X       |\n" +
        "|   X X X X X X       |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "=======================\n"
    )

    assertThrows[IndexOutOfBoundsException] {
      board.place(new BlockShapeSquare(new Vec2d(7, 7)))
    }
  }


   test("remove full rows test") {
    val board = new Board(10, 5, null)
    board.place(new BlockShapeT(new Vec2d(2, 0)))
    board.place(new BlockShapeL(new Vec2d(5, 0)))
    board.place(new BlockShapeL2(new Vec2d(8, 0)))
    val tBlock = new BlockShapeT(new Vec2d(0, 1))
    tBlock.move(Right(RotateDirection.RotateRight))
    board.place(tBlock)

    assert(board.toString ==
      "=======================\n" +
        "|                     |\n" +
        "|                     |\n" +
        "| X                   |\n" +
        "| X X X       X X     |\n" +
        "| X X X X X X X X X X |\n" +
        "=======================\n"
    )

    board.removeFullRows(tBlock)
    assert(board.toString ==
      "=======================\n" +
        "|                     |\n" +
        "|                     |\n" +
        "|                     |\n" +
        "| X                   |\n" +
        "| X X X       X X     |\n" +
        "=======================\n"
    )
  }

   test("full rows amount test") {
    var board = new Board(10, 5, null)
    board.place(new BlockShapeT(new Vec2d(2, 0)))
    board.place(new BlockShapeL(new Vec2d(5, 0)))
    board.place(new BlockShapeL2(new Vec2d(8, 0)))
    val tBlock = new BlockShapeT(new Vec2d(0, 1))
    tBlock.move(Right(RotateDirection.RotateRight))
    board.place(tBlock)
    assert(board.removeFullRows(tBlock) == 1)
    board.place(new BlockShapeSquare(new Vec2d(8, 0)))
    board.place(new BlockShapeL(new Vec2d(4, 0)))
    board.place(new BlockShapeL2(new Vec2d(3, 1)))
    board.place(new BlockShapeS(new Vec2d(1, 2)))
    board.place(new BlockShapeS(new Vec2d(6, 2)))
    board.place(new BlockShapeS2(new Vec2d(8, 2)))
    assert(board.removeFullRows(tBlock) == 2)
    val iBlock = new BlockShapeI(new Vec2d(4, 2))
    iBlock.move(Right(RotateDirection.RotateRight))
    board.place(iBlock)
    val iBlock2 = new BlockShapeI(new Vec2d(3, 2))
    iBlock2.move(Right(RotateDirection.RotateRight))
    board.place(iBlock2)
    val iBlock3 = new BlockShapeI(new Vec2d(9, 2))
    iBlock3.move(Right(RotateDirection.RotateRight))
    board.place(iBlock3)
    board.place(new BlockShapeS(new Vec2d(6, 2)))
    board.place(new BlockShapeS2(new Vec2d(8, 2)))
    board.place(new BlockShapeS(new Vec2d(1, 2)))
    val iBlock4 = new BlockShapeI(new Vec2d(2, 3))
    iBlock4.move(Right(RotateDirection.RotateRight))
    board.place(iBlock4)
    assert(board.removeFullRows(tBlock) == 3)
    board = new Board(4, 5, null)
    val iBlock5 = new BlockShapeI(new Vec2d(0, 3))
    iBlock5.move(Right(RotateDirection.RotateRight))
    board.place(iBlock5)
    val iBlock6 = new BlockShapeI(new Vec2d(1, 3))
    iBlock6.move(Right(RotateDirection.RotateRight))
    board.place(iBlock6)
    val iBlock7 = new BlockShapeI(new Vec2d(2, 3))
    iBlock7.move(Right(RotateDirection.RotateRight))
    board.place(iBlock7)
    val iBlock8 = new BlockShapeI(new Vec2d(3, 3))
    iBlock8.move(Right(RotateDirection.RotateRight))
    board.place(iBlock8)
    assert(board.removeFullRows(iBlock8) == 4)
   }

   test("can block be placed test") {
     val board = new Board(4, 5, null)
     val iBlock5 = new BlockShapeI(new Vec2d(0, 3))
     iBlock5.move(Right(RotateDirection.RotateRight))
     assert(board.canBlockBePlaced(iBlock5))
     board.place(iBlock5)
     val iBlock6 = new BlockShapeI(new Vec2d(0, 3))
     iBlock6.move(Right(RotateDirection.RotateRight))
     assert(!board.canBlockBePlaced(iBlock6))
   }
}
