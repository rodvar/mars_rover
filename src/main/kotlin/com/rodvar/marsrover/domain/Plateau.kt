package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Plateau(val maxX: Int, val maxY: Int) {

    var rovers: MutableList<Rover> = mutableListOf()
    val matrix: MutableList<MutableList<Rover?>> = mutableListOf()

    init {
        for (x in 0..maxX) {
            matrix.add(mutableListOf())
            for (y in 0..maxY)
                matrix.get(x).add(null)
        }
    }

    fun position(rover: Rover?, x: Int, y: Int) {
        if (x > maxX || y > maxY || x < 0 || y < 0)
            throw IllegalArgumentException("Cannot position rover on a non existant cell")
        updateRoverPosition(rover, x, y)
        if (rover != null) {
            this.rovers.add(rover)
            rover.plateau = this
        }
    }

    private fun updateRoverPosition(rover: Rover?, x: Int, y: Int) {
        if (rover?.lastPositionIsValid()!!)
            this.matrix.get(rover.lastX).remove(rover)
        this.matrix.get(x).add(y, rover)
        rover.lastX = x
        rover.lastY = y
    }

    fun move(roverId: Int, instructionsString: String) {
        findRoverById(roverId).move(instructionsString)
    }

    fun move(rover: Rover) {
        val foundRover = this.matrix.get(rover.lastX).get(rover.lastY)
        assert(rover === foundRover)
        when (foundRover?.orientation) {
            Rover.Orientation.N -> moveVertical(rover, 1)
            Rover.Orientation.S -> moveVertical(rover, -1)
            Rover.Orientation.E -> moveHorizontal(rover, 1)
            Rover.Orientation.W -> moveHorizontal(rover, -1)
        }
    }

    private fun moveVertical(rover: Rover, amount: Int) {
        val currentX = rover.lastX
        val currentY = rover.lastY
        val nextCell = this.matrix.get(rover.lastX).get(rover.lastY + amount)
        if (nextCell == null) { // ok to move
            this.updateRoverPosition(rover, rover.lastX, rover.lastY + amount)
        } else {
	    println("Ignoring movement to avoid collision")
	}
    }

    private fun moveHorizontal(rover: Rover, amount: Int) {
        val currentX = rover.lastX
        val currentY = rover.lastY
        val nextCell = this.matrix.get(rover.lastX + amount).get(rover.lastY)
        if (nextCell == null) { // ok to move
            this.updateRoverPosition(rover, rover.lastX + amount, rover.lastY)
        } else throw IllegalArgumentException("Cannot move rover there")

    }

    private fun findRoverById(roverId: Int) = this.rovers.get(roverId - 1)

    fun isValid(): Boolean {
        return this.maxX >= 0 && this.maxY >= 0
    }
}
