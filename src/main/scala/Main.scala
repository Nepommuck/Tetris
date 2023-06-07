import scala.collection.mutable.ArrayBuffer
import Blocks.*
import Utils.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.KeyEvent
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text
import scalafx.Includes.*
import scalafx.beans.binding.{Bindings, StringBinding}
import scalafx.scene.control.Label

object Main extends JFXApp3 {
    override def start(): Unit = {
        val gameCanvas = new Canvas(300, 600)
        val gameEngine = new GameEngine(Vec2d(10,20), gameCanvas)
        stage = new PrimaryStage {
            title = "Tetris"
            scene = new Scene {
                content = new VBox {
                    padding = Insets(20)
                    children = Seq(
                        new Text {
                            text <== Bindings.createStringBinding(() => "Score: " + gameEngine.score.toString)
                            //text <== gameEngine.score.asString //TODO doesnt work
                            style = "-fx-font-size: 20pt"
                        },
                        gameCanvas,
                    )
                }
                onKeyPressed = key => gameEngine.moveBlock(MoveParser.parse(key.getText))
            }
        }

        new Thread(gameEngine).start()
    }
}

