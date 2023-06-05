import Blocks.{BlockShapeT, PlayingBlock}
import Utils.{MoveDirection, RotateDirection, Vec2d}

import scala.concurrent.duration.{Duration, SECONDS}


class GameEngine(private val boardSize: Vec2d):
    private val gameBoard: Board = Board(boardSize.x, boardSize.y)
    private var playingBlock: PlayingBlock = null

    private val sleepDuration: Duration = Duration(0.8, SECONDS)
//    private val sleepDuration: Duration = Duration(0.01, SECONDS)
    private val startingPosition = Vec2d(boardSize.x / 2, boardSize.y - 2)
    private var score: Int = 0
    private val scoring = List(0, 100, 300, 500, 800)

    def increaseScore(linesCleared: Int): Unit = {
        score += scoring(linesCleared)
    }

    def simulate(moves: List[Either[MoveDirection, RotateDirection]]): Unit = {
        playingBlock = BlockShapeT("r", startingPosition)

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

                playingBlock = BlockShapeT("r", startingPosition)
            }

            Thread.sleep(sleepDuration.toMillis)
        }
        gameBoard.display(playingBlock, Some(score))
    }
