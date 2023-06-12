import org.scalatest.funsuite.AnyFunSuite
import Utils.Vec2d


class Vec2dTest extends AnyFunSuite:
    test("Vec2d attributes") {
        assert(Vec2d(12, 15).x === 12)
        assert(Vec2d(12, 15).y === 15)

        assert(Vec2d(0, -1989).x === 0)
        assert(Vec2d(0, -1989).y === -1989)
    }

    test("Vec2d equals") {
        assert(Vec2d(0, 0) === Vec2d(0, 0))
        assert(Vec2d(8, -2) === Vec2d(8, -2))
        assert(Vec2d(1914, 1918) === Vec2d(1914, 1918))

        assert(Vec2d(1, 1) != Vec2d(2, 2))
        assert(Vec2d(8, -2) != Vec2d(1, -2))
        assert(Vec2d(0, 1) != Vec2d(0, -1))
    }

    test("Vec2d negation") {
        assert(-Vec2d(0, 1) === Vec2d(0, -1))
        assert(-Vec2d(21, -37) === Vec2d(-21, 37))
        assert(-Vec2d(0, 0) === Vec2d(0, 0))
    }

    test("Vec2d addition") {
        assert(Vec2d(1, 2) + Vec2d(3, 5) === Vec2d(4, 7))
        assert(Vec2d(0, 0) + Vec2d(8, 1) === Vec2d(8, 1))
        assert(Vec2d(-5, -6) + Vec2d(6, 5) === Vec2d(1, -1))
    }

    test("Vec2d subtraction") {
        assert(Vec2d(1, 2) - Vec2d(3, 5) === Vec2d(-2, -3))
        assert(Vec2d(0, 0) - Vec2d(8, 1) === Vec2d(-8, -1))
        assert(Vec2d(-5, -6) - Vec2d(6, 5) === Vec2d(-11, -11))
    }

    test("Vec2d min") {
        assert(Vec2d(1, 2).min(Vec2d(3, 5)) === Vec2d(1, 2))
        assert(Vec2d(1, 2).min(Vec2d(3, -5)) === Vec2d(1, -5))
        assert(Vec2d(1, 2).min(Vec2d(-3, -5)) === Vec2d(-3, -5))
    }

    test("Vec2d max") {
        assert(Vec2d(1, 2).max(Vec2d(3, 5)) === Vec2d(3, 5))
        assert(Vec2d(1, 2).max(Vec2d(3, -5)) === Vec2d(3, 2))
        assert(Vec2d(1, 2).max(Vec2d(-3, -5)) === Vec2d(1, 2))
    }

    test("Vec2d smaller") {
        assert(Vec2d(1, 1) < Vec2d(2, 2))
        assert(Vec2d(1, 1) < Vec2d(1, 2) === false)
        assert(Vec2d(1, 1) < Vec2d(2, 1) === false)
    }

    test("Vec2d smaller or equal") {
        assert(Vec2d(1, 1) <= Vec2d(2, 2))
        assert(Vec2d(1, 1) <= Vec2d(1, 2))
        assert(Vec2d(1, 1) <= Vec2d(1, 1))
        assert(Vec2d(1, 1) <= Vec2d(0, 1) === false)
    }

    test("Vec2d greater") {
        assert(Vec2d(3, 3) > Vec2d(2, 2))
        assert(Vec2d(2, 2) > Vec2d(1, 2) === false)
        assert(Vec2d(2, 2) > Vec2d(2, 1) === false)
    }

    test("Vec2d greater or equal") {
        assert(Vec2d(3, 3) >= Vec2d(2, 2))
        assert(Vec2d(2, 2) >= Vec2d(1, 2))
        assert(Vec2d(2, 2) >= Vec2d(1, 1))
        assert(Vec2d(15, -1) >= Vec2d(0, 1) === false)
    }

    test("Vec2d to tuple") {
        assert(Vec2d(3, 1).toTuple === (3, 1))
        assert(Vec2d(0, 1111).toTuple === (0, 1111))
        assert(Vec2d(-18, -81).toTuple === (-18, -81))
    }

    test("Vec2d to string") {
        assert(Vec2d(3, 1).toString === "v(3, 1)")
        assert(Vec2d(0, 1111).toString === "v(0, 1111)")
        assert(Vec2d(-18, -81).toString === "v(-18, -81)")
    }