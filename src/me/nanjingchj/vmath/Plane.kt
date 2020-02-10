package me.nanjingchj.vmath

data class Plane(val p0: Vector, val m1: Vector, val m2: Vector) {
    val knownPoint: Point
        get() = p0.toPoint()

    val normal: Vector
        get() = m1.cross(m2)

    fun isParallelTo(that: Line): Boolean {
        return normal.dot(that.directionVector) == 0.0
    }
}