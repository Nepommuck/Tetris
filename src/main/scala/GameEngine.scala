import Blocks.{BlockShapeT, PlayingBlock}
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

    private val moves = MoveParser.parse(
    List(
        // First block
        "d", "d", "l", "d", "d", "d",
        // Second block
        "rr", "l", "l", "d", "d", "d",
        // Third block
        "rl", "r", "d", "d", "d", "d",
        // Fourth block
        "d", "d", "d", "rr", "rr", "d",
    ))

    def increaseScore(linesCleared: Int): Unit = {
        score += scoring(linesCleared)
    }

    def getRandomBlockColor: Color = Random.shuffle(List(Color.Red,Color.Blue,Color.Green,Color.Aqua)).head

    def run(): Unit = {
        playingBlock = BlockShapeT(getRandomBlockColor, startingPosition)

//        while (true) {
        for (move <- moves) {
            gameBoard.display(playingBlock, Some(score))

            if (gameBoard.canBlockMove(playingBlock, move)) {
                playingBlock.move(move)
            }
            else if (move == Left(MoveDirection.Down)) {
                gameBoard.place(playingBlock)
                val fullRows = gameBoard.removeFullRows(playingBlock)
                increaseScore(fullRows)

                playingBlock = BlockShapeT(getRandomBlockColor, startingPosition)
            }

            Thread.sleep(sleepDuration.toMillis)
        }
        gameBoard.display(playingBlock, Some(score))
    }
