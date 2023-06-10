import scala.collection.mutable.ArrayBuffer
import Blocks.*
import Utils.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.KeyEvent
import scalafx.scene.layout.{AnchorPane, VBox}
import scalafx.scene.text.Text
import scalafx.Includes.*
import scalafx.beans.binding.{Bindings, StringBinding}
import scalafx.scene.control.{Button, Label}

object Main extends JFXApp3 {
    override def start(): Unit = {
        val gameCanvas = new Canvas(300, 600)
        val scoreText = new Text {
            text = "Score: 0"
            style = "-fx-font-size: 20pt"
        }

        val playButton = new Button("New Game")
        playButton.setFocusTraversable(false)

        val gameEngine = new GameEngine(Vec2d(10, 20), gameCanvas, scoreText, playButton)
        playButton.setOnAction(e => gameEngine.startGame())

        val anchorPane = new AnchorPane()
        anchorPane.getChildren.addAll(gameCanvas, playButton)
        AnchorPane.setTopAnchor(playButton,gameCanvas.getHeight / 2)
        AnchorPane.setLeftAnchor(playButton, gameCanvas.getWidth / 2.6)

        stage = new PrimaryStage {
            title = "Tetris"
            scene = new Scene {
                content = new VBox {
                    padding = Insets(20)
                    children = Seq(
                        scoreText,
                        anchorPane
                    )
                }

                onKeyPressed = key => gameEngine.setPlayerAction(MoveParser.parse(key.getText))
            }
        }
        stage.setResizable(false)

        new Thread(gameEngine).start()
    }
}

