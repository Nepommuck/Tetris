import Utils.PlayerAction
import Utils.PlayerAction.*

object MoveParser:
    def parse(input: String): PlayerAction =
        input match {
            case "s" => MoveDown
            case "a" => MoveLeft
            case "d" => MoveRight

            case "e" => RotateRight
            case "w" => RotateRight
            case "q" => RotateLeft

            case "x" => Drop
            case _ => null
        }

