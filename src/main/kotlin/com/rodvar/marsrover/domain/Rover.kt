package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Rover(val id: Int, var orientation: Orientation, var x: Int, var y: Int) {

    var plateau: Plateau? = null

    enum class Orientation {
        N, S, W, E
    }

    /**
     * Moves this rover and let's the plateau (if any) know about it's location if changed
     */
    fun move(instructionsString: String) {

        for (i in 0 until instructionsString.length) {
            when (instructionsString[i]) {
                'L' -> rotateLeft()
                'R' -> rotateRight()
                'M' -> moveForward()
            }
        }
    }

    /**
     * Moves this rover forward.
     * If it has a plateau, it tells the plateau that it has moved away from its position
     */
    private fun moveForward() {
        println("move 1 fwd")
        var x = this.x
        var y = this.y
        when (this.orientation) {
            Orientation.N -> y++
            Orientation.S -> y--
            Orientation.E -> x++
            Orientation.W -> x--
        }
        if (this.plateau?.busy(x, y) == false)
            this.plateau?.move(this, x, y)
        this.x = x
        this.y = y
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