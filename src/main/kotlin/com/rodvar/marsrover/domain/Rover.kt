package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Rover(val id: Int, var orientation: Orientation) {

    lateinit var plateau: Plateau
    var lastX: Int = -1
    var lastY: Int = -1

    enum class Orientation {
        N, S, W, E
    }

    fun move(instructionsString: String) {
        for (i in 0..instructionsString.length - 1) {
            when (instructionsString[i]) {
                'L' -> rotateLeft()
                'R' -> rotateRight()
                'M' -> moveForward()
            }
        }
    }

    private fun moveForward() {
        println("move 1 fwd")
        this.plateau.move(this)
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

    fun lastPositionIsValid(): Boolean {
        return this.lastX >= 0 && this.lastY >= 0
    }
}