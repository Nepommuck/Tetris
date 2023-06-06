import Blocks.{BlockShapeI, BlockShapeL, BlockShapeL2, BlockShapeS, BlockShapeS2, BlockShapeSquare, BlockShapeT, PlayingBlock}
import Utils.{MoveDirection, RotateDirection, Vec2d}
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color

import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.Random

class GameEngine(private val boardSize: Vec2d, private val gameCanvas: Canvas) extends Runnable:
    private val gameBoard: Board = Board(boardSize.x, boardSize.y, gameCanvas)
    private var playingBlock: PlayingBlock = null

    private val sleepDuration: Duration = Duration(0.8, SECONDS)
//    private val sleepDuration: Duration = Duration(0.01, SECONDS)
    private val startingPosition = Vec2d(boardSize.x / 2, boardSize.y - 2)
    private var score: Int = 0
    private val scoring = List(0, 100, 300, 500, 800)

    def increaseScore(linesCleared: Int): Unit = {
        score += scoring(linesCleared)
    }

    def getRandomBlockColor: Color = Random.shuffle(List(Color.Red,Color.Blue,Color.Green,Color.Aqua)).head

    def spawnNewBlock(): Unit = {

        Random.nextInt(7) match {
            case 0 => playingBlock = BlockShapeT(getRandomBlockColor, startingPosition)
            case 1 => playingBlock = BlockShapeI(getRandomBlockColor, startingPosition)
            case 2 => playingBlock = BlockShapeL(getRandomBlockColor, startingPosition)
            case 3 => playingBlock = BlockShapeL2(getRandomBlockColor, startingPosition)
            case 4 => playingBlock = BlockShapeS(getRandomBlockColor, startingPosition)
            case 5 => playingBlock = BlockShapeS2(getRandomBlockColor, startingPosition)
            case 6 => playingBlock = BlockShapeSquare(getRandomBlockColor, startingPosition)
        }

    }

    def moveBlock(move: Either[MoveDirection, RotateDirection]) : Unit = {
        if move == null then return
        if (gameBoard.canBlockMove(playingBlock, move)) {
            playingBlock.move(move)
        }
        else if (move == Left(MoveDirection.Down)) {
            gameBoard.place(playingBlock)
            val fullRows = gameBoard.removeFullRows(playingBlock)
            increaseScore(fullRows)
            spawnNewBlock()
        }
        gameBoard.display(playingBlock, Some(score))
    }

    def run(): Unit = {
        spawnNewBlock()
        gameBoard.display(playingBlock, Some(score))
        while (true) {
            Thread.sleep(sleepDuration.toMillis)
            moveBlock(Left(MoveDirection.Down))
        }
    }
