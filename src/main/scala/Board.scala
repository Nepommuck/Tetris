import scala.collection.mutable.ArrayBuffer
import Blocks.{Block, PlayingBlock, SteadyBlock}
import Utils.{MoveDirection, RotateDirection, Vec2d}
import com.sun.tools.classfile.TypeAnnotation.Position


class Board(val width: Int, val height: Int):
    private val boardArray: ArrayBuffer[ArrayBuffer[Block]] =
        generateBoardArray()

    private val minCorner = Vec2d(0, 0)
    private val maxCorner = Vec2d(width - 1, height - 1)

    private def generateBoardArray():  ArrayBuffer[ArrayBuffer[Block]] = {
        val array = ArrayBuffer[ArrayBuffer[Block]]()
        for (_ <- 0 until height) {
            val row = ArrayBuffer[Block]()
            for (_ <- 0 until width) {
                row += null
            }
            array += row
        }
        array
    }

    private def getBlockAtPosition(position: Vec2d): Block = {
        boardArray(position.y)(position.x)
    }

    private def placeBlockAtPosition(position: Vec2d, block: Block): Unit = {
        boardArray(position.y)(position.x) = block
    }

    def display(playingBlock: PlayingBlock, score: Option[Int] = None): Unit = {
        val playingLocations: List[(Int, Int)] = playingBlock.content
          .map(v => v + playingBlock.position)
          .map(v => v.toTuple)

        val stringBuilder = new StringBuilder
        val horizontalLine = List.fill(2 * width + 3)('=').mkString

        score match
            case Some(value) => stringBuilder.append(s"Score: $value \n")
            case None => None

        stringBuilder.append(horizontalLine)
        stringBuilder.append('\n')

        for (y <- height-1 to 0 by -1) {
            stringBuilder.append('|')
            for (x <- 0 until width) {
                val block = if playingLocations.contains((x, y)) then '#' else boardArray(y)(x)
                stringBuilder.append(' ')
                stringBuilder.append(
                    if (block == null) " " else block
                )
            }
            stringBuilder.append(" |\n")
        }
        stringBuilder.append(horizontalLine)
        stringBuilder.append('\n')

        println(stringBuilder.toString())
    }

    def canBlockMove(playingBlock: PlayingBlock, direction: Either[MoveDirection, RotateDirection]): Boolean = {
        val blockFutureLocations: List[Vec2d] = direction match
            case Left(moveDirection) => playingBlock.content
              .map(v => v + playingBlock.position + moveDirection.toVec2d)

            case Right(rotateDirection) => playingBlock.content
              .map(v => v.rotated(rotateDirection))
              .map(v => v + playingBlock.position)

        canBlockBePlaced(blockFutureLocations)
    }

    private def canBlockBePlaced(locations: List[Vec2d]): Boolean = {
        for (v <- locations) {
            if (!(v >= minCorner && v <= maxCorner)) {
                return false
            }
            if (getBlockAtPosition(v) != null) {
                return false
            }
        }
        true
    }

    def place(playingBlock: PlayingBlock): Unit = {
        val blockLocations: List[Vec2d] = playingBlock.content
          .map(v => v + playingBlock.position)

        for (v <- blockLocations) {
            placeBlockAtPosition(v, SteadyBlock(playingBlock.color))
        }
    }

    // Returns the number of removed full rows
    def removeFullRows(playingBlock: PlayingBlock): Int = {
        val blockYValues = playingBlock.contentPositions.map(v => v.y)

        // Finding full rows
        var fullRows = List[Int]()
        for (y <- blockYValues.max to blockYValues.min by -1) {
            var isFull = true
            for (x <- 0 until width)
                if (boardArray(y)(x) == null) {
                    isFull = false
                    // break
                }
            if (isFull)
                fullRows = fullRows :+ y
        }
        // Putting values one row lower
        for (row <- fullRows)
            for (y <- row until height-1)
                boardArray(y) = boardArray(y+1)

        for (x <- 0 until width)
            boardArray(height-1)(x) = null

        fullRows.length
    }
