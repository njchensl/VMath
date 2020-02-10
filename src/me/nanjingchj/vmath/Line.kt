package me.nanjingchj.vmath

import kotlin.math.abs

@Suppress("MemberVisibilityCanBePrivate")
data class Line(val p0: Vector, val directionVector: Vector) {
    val vectorForm: String
        get() = "[x, y, z] = " + p0.getCartesianForm() + " + t" + directionVector.getCartesianForm()

    fun intersectsWith(that: Line): Boolean {
        return if (isParallelTo(that)) {
            false
        } else getDistanceTo(that) == 0.0
    }

    val knownPoint: Point?
        get() = p0.toPoint()

    fun isParallelTo(that: Line): Boolean {
        val m1 = directionVector
        val m2 = that.directionVector
        return m1.isScalarMultipleOf(m2)
    }

    fun isSkewWith(that: Line): Boolean {
        return if (isParallelTo(that)) {
            false
        } else getDistanceTo(that) != 0.0
    }

    fun getDistanceTo(q: Point?): Double {
        val p = knownPoint
        val pq = p!!.directionTo(q)
        val m = directionVector
        val area = abs(m.cross(pq).magnitude)
        return area / m.magnitude
    }

    fun getDistanceTo(that: Line): Double {
        if (isParallelTo(that)) {
            return getDistanceTo(that.knownPoint)
        }
        val n = directionVector.cross(that.directionVector)
        val pq = knownPoint!!.directionTo(that.knownPoint)
        return abs(pq.projectOnto(n).magnitude)
    }

    fun coincidesWith(that: Line): Boolean {
        val m1 = directionVector
        val m2 = that.directionVector
        val m3 = knownPoint!!.directionTo(that.knownPoint)
        return m1.isScalarMultipleOf(m2) && m1.isScalarMultipleOf(m3)
    }

    override fun toString(): String {
        return vectorForm
    }
}