package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Rover(val id: Int, var orientation: Orientation) {

    var plateau: Plateau? = null
    var x: Int = Int.MIN_VALUE
    var y: Int = Int.MIN_VALUE

    enum class Orientation {
        N, S, W, E
    }

    fun move(instructionsString: String) {
        if (this.plateau == null)
            throw IllegalStateException("Cannot move: rover is not in a plateau")

        for (i in 0 until instructionsString.length) {
            when (instructionsString[i]) {
                'L' -> rotateLeft()
                'R' -> rotateRight()
                'M' -> moveForward()
            }
        }
    }

    private fun moveForward() {
        println("move 1 fwd")
        this.plateau?.move(this)
    }

    fun rotateRight() {
        println("rotate right")
        when (this.orientation) {
            Orientation.N -> this.orientation = Orientation.E
            Orientation.S -> this.orientation = Orientation.W
            Orientation.W -> this.orientation = Orientation.N
            Orientation.E -> this.orientation = Orientation.S
        }
    }

    fun rotateLeft() {
        println("rotate left")
        when (this.orientation) {
            Orientation.N -> this.orientation = Orientation.W
            Orientation.S -> this.orientation = Orientation.E
            Orientation.W -> this.orientation = Orientation.S
            Orientation.E -> this.orientation = Orientation.N
        }
    }
}