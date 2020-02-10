package me.nanjingchj.vmath

data class Point(val x: Double, val y: Double, val z: Double) {

    constructor(x: Double, y: Double) : this(x, y, 0.0)

    constructor() : this(0.0, 0.0, 0.0)

    override fun equals(other: Any?): Boolean {
        if (other is Point) {
            return this.equals(other)
        }
        return false
    }

    fun equals(that: Point): Boolean {
        return x == that.x && y == that.y && z == that.z
    }

    fun directionTo(that: Point?): Vector {
        return Vector(that!!.x - x, that.y - y, that.z - z)
    }

    fun distanceTo(that: Point?): Double {
        return directionTo(that).magnitude
    }

    fun move(v: Vector): Point? {
        return toVector().add(v).toPoint()
    }

    fun isOn(that: Line): Boolean {
        val q = this
        val p = that.knownPoint
        val pq = p!!.directionTo(q)
        return pq.isScalarMultipleOf(that.directionVector)
    }

    fun isOn(that: Plane): Boolean {
        val q = this
        val p = that.knownPoint
        val pq = p.directionTo(q)
        val n = that.normal
        return pq.dot(n) == 0.0
    }

    fun toVector(): Vector {
        return Vector(x, y, z)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }
}