import scala.collection.mutable.ArrayBuffer
import Blocks.*
import Utils.*


object Main:

    def main(args: Array[String]): Unit = {
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

