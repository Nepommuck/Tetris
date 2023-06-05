package Utils

enum MoveDirection:
    case Up, Right, Down, Left

    def toVec2d: Vec2d = {
        this match
            case Up => Vec2d(0, 1)
            case Down => Vec2d(0, -1)
            case Left => Vec2d(-1, 0)
            case Right => Vec2d(1, 0)
    }

    def toTuple: (Int, Int) =
        toVec2d.toTuple
