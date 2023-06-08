package Utils

enum PlayerAction {
    case MoveRight, MoveLeft, MoveDown, RotateRight, RotateLeft, Drop

    def toDirection: Either[MoveDirection, RotateDirection] =
        this match {
            case MoveRight => Left(MoveDirection.Right)
            case MoveLeft => Left(MoveDirection.Left)
            case MoveDown => Left(MoveDirection.Down)

            case RotateRight => Right(RotateDirection.RotateRight)
            case RotateLeft => Right(RotateDirection.RotateLeft)
            
            case _ => null
        }

//    def isMoveDirection(): Boolean =
//        toMoveDirection() != null
//
//    def isRotateDirection(): Boolean =
//        toRotateDirection() != null
//        
//    def toMoveDirection(): MoveDirection = {
//        this match {
//            case MoveRight => MoveDirection.Right
//            case MoveLeft => MoveDirection.Left
//            case MoveDown => MoveDirection.Down
//            case _ => null
//        }
//    }
//
//    def toRotateDirection(): RotateDirection = {
//        this match {
//            case RotateRight => RotateDirection.RotateRight
//            case RotateLeft => RotateDirection.RotateLeft
//            case _ => null
//        }
//    }
}