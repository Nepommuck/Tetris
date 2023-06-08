import Blocks.{BlockShapeI, BlockShapeL, BlockShapeL2, BlockShapeS, BlockShapeS2, BlockShapeSquare, BlockShapeT, PlayingBlock}
import Utils.{PlayerAction, Vec2d}

import scalafx.scene.canvas.Canvas
import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.Random

class GameEngine(private val boardSize: Vec2d, private val gameCanvas: Canvas) extends Runnable:
    private val gameBoard: Board = Board(boardSize.x, boardSize.y, gameCanvas)
    private var playingBlock: PlayingBlock = null

    private val sleepDuration: Duration = Duration(0.8, SECONDS)
//    private val sleepDuration: Duration = Duration(0.01, SECONDS)
    private val startingPosition = Vec2d(boardSize.x / 2, boardSize.y - 2)
    var score: Int = 0
    private val scoring = List(0, 100, 300, 500, 800)

    def increaseScore(linesCleared: Int): Unit = {
        score += scoring(linesCleared)
    }

    //def getRandomBlockColor: Color = Random.shuffle(List(Color.Red,Color.Blue,Color.Green,Color.Aqua)).head

    def spawnNewBlock(): Unit = {

        Random.nextInt(7) match {
            case 0 => playingBlock = BlockShapeT(startingPosition)
            case 1 => playingBlock = BlockShapeI(startingPosition)
            case 2 => playingBlock = BlockShapeL(startingPosition)
            case 3 => playingBlock = BlockShapeL2(startingPosition)
            case 4 => playingBlock = BlockShapeS(startingPosition)
            case 5 => playingBlock = BlockShapeS2(startingPosition)
            case 6 => playingBlock = BlockShapeSquare(startingPosition)
        }

    }

    def moveBlock(playerAction: PlayerAction) : Unit = {
        val moveAsDirection = if (playerAction != null) playerAction.toDirection else null

        if (moveAsDirection != null) {
            if (gameBoard.canBlockMove(playingBlock, moveAsDirection))
                playingBlock.move(moveAsDirection)

            else if (playerAction == PlayerAction.MoveDown) {
                gameBoard.place(playingBlock)
                val fullRows = gameBoard.removeFullRows(playingBlock)
                increaseScore(fullRows)
                spawnNewBlock()
            }
        }
        else if (playerAction == PlayerAction.Drop)
            println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")

        gameBoard.display(playingBlock, Some(score))
    }

    def run(): Unit = {
        spawnNewBlock()
        gameBoard.display(playingBlock, Some(score))
        while (true) {
            Thread.sleep(sleepDuration.toMillis)
            moveBlock(PlayerAction.MoveDown)
        }
    }
