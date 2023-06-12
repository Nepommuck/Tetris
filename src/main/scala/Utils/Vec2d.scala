package Utils

import scala.annotation.targetName

class Vec2d(val x: Int, val y: Int) {

    def toTuple: (Int, Int) = {
        (x, y)
    }

    def rotated(direction: RotateDirection): Vec2d = {
        direction match
            case RotateDirection.RotateLeft => Vec2d(-y, x)
            case RotateDirection.RotateRight => Vec2d(y, -x)
    }
    
    def unary_- : Vec2d =
        Vec2d(-x, -y)
    
    @targetName("add")
    def +(other: Vec2d): Vec2d =
        Vec2d(x + other.x, y + other.y)

    @targetName("subtract")
    def -(other: Vec2d): Vec2d =
        Vec2d(x - other.x, y - other.y)

    def min(other: Vec2d): Vec2d =
        Vec2d(x.min(other.x), y.min(other.y))

    def max(other: Vec2d): Vec2d =
        Vec2d(x.max(other.x), y.max(other.y))

    @targetName("greaterThan")
    def >(other: Vec2d): Boolean =
        x > other.x && y > other.y

    @targetName("smallerThan")
    def <(other: Vec2d): Boolean =
        x < other.x && y < other.y

    @targetName("greaterOrEqual")
    def >=(other: Vec2d): Boolean =
        x >= other.x && y >= other.y

    @targetName("smallerOrEqual")
    def <=(other: Vec2d): Boolean =
        x <= other.x && y <= other.y
        
    override def equals(other: Any): Boolean =
        other match {
            case that: Vec2d =>
                this.x == that.x && this.y == that.y
            case _ =>
                false
        }

    override def hashCode(): Int =
        super.hashCode()

    override def toString: String =
        "v(" + x +", " + y + ")"
}
