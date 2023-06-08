import Blocks.{BlockShapeI, BlockShapeL, BlockShapeL2, BlockShapeS, BlockShapeS2, BlockShapeSquare, BlockShapeT, PlayingBlock}
import Utils.{PlayerAction, Vec2d}

import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.Random
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Button
import scalafx.scene.text.Text


class GameEngine(private val boardSize: Vec2d, private val gameCanvas: Canvas, private val scoreText: Text, private val playButton: Button) extends Runnable:
    private var playing: Boolean = false
    private val gameBoard: Board = Board(boardSize.x, boardSize.y, gameCanvas)
    private var playingBlock: PlayingBlock = null
    private var playerAction: PlayerAction = null

    private val autoMovementDuration: Duration = Duration(0.5, SECONDS)
    private var timeElapsed: Duration = Duration(0.0, SECONDS)

    private val startingPosition = Vec2d(boardSize.x / 2, boardSize.y - 2)
    var score: Int = 0
    private val scoring = List(0, 100, 300, 500, 800)
    private val FPS: Int = 24

    def increaseScore(linesCleared: Int): Unit = {
        score += scoring(linesCleared)
        scoreText.text = "Score: " + score
    }

    //def getRandomBlockColor: Color = Random.shuffle(List(Color.Red,Color.Blue,Color.Green,Color.Aqua)).head

    def spawnNewBlock(): Boolean = {
        val newBlock = Random.nextInt(7) match {
            case 0 => BlockShapeT(startingPosition)
            case 1 => BlockShapeI(startingPosition)
            case 2 => BlockShapeL(startingPosition)
            case 3 => BlockShapeL2(startingPosition)
            case 4 => BlockShapeS(startingPosition)
            case 5 => BlockShapeS2(startingPosition)
            case 6 => BlockShapeSquare(startingPosition)
        }

        if (gameBoard.canBlockBePlaced(newBlock)) {
            playingBlock = newBlock
            true
        }
        else
            false
    }

    def setPlayerAction(newPlayerAction: PlayerAction): Unit = {
        playerAction = newPlayerAction
        timeElapsed = Duration(0, SECONDS)
    }


    def handleAction() : Unit = {
        var moveAsDirection = if (playerAction != null) playerAction.toDirection else null

        // Move to the very bottom
        if (playerAction == PlayerAction.Drop) {
            moveAsDirection = PlayerAction.MoveDown.toDirection
            while (gameBoard.canBlockMove(playingBlock, moveAsDirection))
                playingBlock.move(moveAsDirection)
        }

        if (moveAsDirection != null) {
            if (gameBoard.canBlockMove(playingBlock, moveAsDirection))
                playingBlock.move(moveAsDirection)

            else if (playerAction == PlayerAction.MoveDown) {
                gameBoard.place(playingBlock)
                val fullRows = gameBoard.removeFullRows(playingBlock)
                increaseScore(fullRows)

                playingBlock = null
            }
        }
        playerAction = null
    }

    def run(): Unit = {
        gameBoard.clear()
        while (true) {
            gameBoard.reset()
            while (!playing) {
                Thread.sleep(50)
            }
            score = 0
            scoreText.text = "Score: 0"
            playButton.visible = false

            spawnNewBlock()
            gameBoard.display(playingBlock, Some(score))

            val timePerFrame = Duration(1.0 / FPS, SECONDS)
            while (playing) {
                Thread.sleep(timePerFrame.toMillis)
                timeElapsed += timePerFrame

                if (timeElapsed >= autoMovementDuration)
                    setPlayerAction(PlayerAction.MoveDown)

                handleAction()
                if (playingBlock == null)
                    playing = spawnNewBlock()
                gameBoard.display(playingBlock, Some(score))
            }
            gameBoard.displayGameOver()
            playButton.visible = true
        }
    }

    def startGame(): Unit = {
        playing = true
    }
