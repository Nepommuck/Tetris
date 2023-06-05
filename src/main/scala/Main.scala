import scala.collection.mutable.ArrayBuffer
import Blocks.*
import Utils.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

object Main extends JFXApp3 {
    override def start(): Unit = {
        stage = new PrimaryStage {
            title = "Tetris"
            scene = new Scene {
                content = new HBox {
                    padding = Insets(20)
                    children = Seq(
                        new Text {
                            text = "Tu bedzie tetris"
                            style = "-fx-font-size: 48pt"
                        }
                    )
                }
            }
        }

        val engine = GameEngine(Vec2d(4, 6))

        val input = List(
            // First block
            "d", "d", "l", "d", "d", "d",
            // Second block
            "rr", "l", "l", "d", "d", "d",
            // Third block
            "rl", "r", "d", "d", "d", "d",
            // Fourth block
            "d", "d", "d", "rr", "rr", "d",
        )
        println(MoveParser.parse(input))
        engine.simulate(MoveParser.parse(input))
    }
}

