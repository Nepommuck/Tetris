import Utils.{MoveDirection, RotateDirection}

object MoveParser:
    def parse(input: String): Either[MoveDirection, RotateDirection] =
        input match {
            case "s" => Left(MoveDirection.Down)
            //case "w" => Left(MoveDirection.Up)
            case "a" => Left(MoveDirection.Left)
            case "d" => Left(MoveDirection.Right)

            case "q" => Right(RotateDirection.RotateRight)
            case "e" => Right(RotateDirection.RotateLeft)
            case _ => null
        }

