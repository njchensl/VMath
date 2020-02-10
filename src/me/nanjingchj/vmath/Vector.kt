package me.nanjingchj.vmath

import kotlin.math.acos
import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate")
data class Vector(val x: Double, val y: Double, val z: Double) {

    constructor(x: Double, y: Double) : this(x, y, 0.0)

    constructor() : this(0.0, 0.0, 0.0)

    val magnitude: Double
        get() = sqrt(dot(this))

    val unitVector: Vector
        get() {
            if (magnitude == 0.0) {
                throw ArithmeticException("A 0 vector has no direction")
            }
            return Vector(x / magnitude, y / magnitude, z / magnitude)
        }

    fun add(that: Vector): Vector {
        return Vector(x + that.x, y + that.y, z + that.z)
    }

    fun subtract(that: Vector): Vector {
        return Vector(x - that.x, y - that.y, z - that.z)
    }

    fun dot(that: Vector?): Double {
        return x * that!!.x + y * that.y + z * that.z
    }

    fun cross(that: Vector?): Vector {
        val x = y * that!!.z - that.y * z
        val y = that.x * z - this.x * that.z
        val z = this.x * that.y - that.x * this.y
        return Vector(x, y, z)
    }

    fun multiply(x: Double): Vector {
        return Vector(x * this.x, x * y, x * z)
    }

    fun toPoint(): Point {
        return Point(x, y, z)
    }

    fun getCartesianForm(): String = "[$x, $y, $z]"

    fun isScalarMultipleOf(that: Vector?): Boolean {
        val multiple = that!!.x / x
        return y * multiple == that.y && z * multiple == that.z
    }

    fun projectOnto(that: Vector?): Vector {
        return that!!.multiply(dot(that) / that.dot(that))
    }

    override fun toString(): String {
        return getCartesianForm()
    }

    companion object {
        @JvmStatic
        fun getAngle(u: Vector, v: Vector): Double {
            return acos(u.dot(v) / u.magnitude * v.magnitude)
        }
    }
}