package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Plateau(val maxX: Int, val maxY: Int) {

    var rovers: MutableList<Rover> = mutableListOf()
    private val matrix: MutableList<MutableList<Rover?>> = mutableListOf()

    init {
        if (!this.isValid())
            throw IllegalArgumentException(ILLEGAL_DIMENSIONS)
        for (x in 0 until maxX) {
            matrix.add(mutableListOf())
            for (y in 0 until maxY)
                matrix[x].add(null)
        }
    }

    /**
     * Positions the rover on this Plateau.
     * @throws IllegalArgumentException if the rover is outside the Plateau
     */
    fun position(rover: Rover) {
        if (!this.isRoverOnPlateau(rover))
            throw IllegalArgumentException("Cannot position rover on a non existent cell")
        updateRoverPosition(rover, rover.x, rover.y)
        if (rover != null) {
            this.rovers.add(rover)
            rover.plateau = this
        }
    }

    /**
     * Change rover position. If it's on plateau, removes it first
     * If after position change is on plateau, adds it back.
     */
    private fun updateRoverPosition(rover: Rover, x: Int, y: Int) {
        if (this.isRoverOnPlateau(rover))
            this.matrix[rover.x].remove(rover)
        rover.x = x
        rover.y = y
        if (this.isRoverOnPlateau(rover))
            this.matrix[x].add(y, rover)
    }

    /**
     * @return true if rover is within this bounderies
     */
    private fun isRoverOnPlateau(rover: Rover): Boolean =
            0 <= rover.x && rover.x < this.maxX && 0 <= rover.y && rover.y < this.maxY


    fun move(roverId: Int, instructionsString: String) {
        findRoverById(roverId).move(instructionsString)
    }

    fun move(rover: Rover) {
        val foundRover = this.matrix.get(rover.x).get(rover.y)
        assert(rover === foundRover)
        when (foundRover?.orientation) {
            Rover.Orientation.N -> moveVertical(rover, 1)
            Rover.Orientation.S -> moveVertical(rover, -1)
            Rover.Orientation.E -> moveHorizontal(rover, 1)
            Rover.Orientation.W -> moveHorizontal(rover, -1)
        }
    }

    private fun moveVertical(rover: Rover, amount: Int) {
        val currentX = rover.x
        val currentY = rover.y
        val nextCell = this.matrix.get(currentX).get(currentY + amount)
        if (nextCell == null) { // ok to move
            this.updateRoverPosition(rover, currentX, currentY + amount)
        } else {
            println("Ignoring movement to avoid collision")
        }
    }

    private fun moveHorizontal(rover: Rover, amount: Int) {
        val currentX = rover.x
        val currentY = rover.y
        val nextCell = this.matrix.get(currentX + amount).get(currentY)
        if (nextCell == null) { // ok to move
            this.updateRoverPosition(rover, currentX + amount, currentY)
        } else throw IllegalArgumentException("Cannot move rover there")

    }

    private fun findRoverById(roverId: Int) = this.rovers.get(roverId - 1)

    fun isValid(): Boolean {
        return this.maxX > 0 && this.maxY > 0
    }

    companion object {
        val ILLEGAL_DIMENSIONS = "Plateau cannot have 0 or negative dimensions"
    }
}
