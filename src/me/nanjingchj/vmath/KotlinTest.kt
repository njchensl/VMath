package me.nanjingchj.vmath

class KotlinTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val v = Vector(0, 1, 2)
            val u = Vector(-1, -2, 3)
            println(u cross v)

        }
    }
}