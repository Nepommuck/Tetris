import Utils.{MoveDirection, RotateDirection}

object MoveParser:
    def parse(input: List[String]): List[Either[MoveDirection, RotateDirection]] = {
        var result = List[Either[MoveDirection, RotateDirection]]()

//        input.foreach {
        for (string <- input) {
            val direction = string match {
                case "d" => Left(MoveDirection.Down)
                case "u" => Left(MoveDirection.Up)
                case "l" => Left(MoveDirection.Left)
                case "r" => Left(MoveDirection.Right)

                case "rr" => Right(RotateDirection.RotateRight)
                case "rl" => Right(RotateDirection.RotateLeft)
                case _ => null
            }
            if (direction != null) {
                result = result :+ direction
            }
        }
        result
    }

