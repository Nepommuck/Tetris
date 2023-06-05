import scala.collection.mutable.ArrayBuffer
import Blocks.*
import Utils.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

object Main extends JFXApp3 {
    override def start(): Unit = {
        val gameCanvas = new Canvas(600, 600)
        stage = new PrimaryStage {
            title = "Tetris"
            scene = new Scene {
                content = new VBox {
                    padding = Insets(20)
                    children = Seq(
                        new Text {
                            text = "niesamowity tetris"
                            style = "-fx-font-size: 20pt"
                        },
                        gameCanvas
                    )
                }
            }
        }

        new Thread(new GameEngine(Vec2d(4,6), gameCanvas)).start()
    }
}

