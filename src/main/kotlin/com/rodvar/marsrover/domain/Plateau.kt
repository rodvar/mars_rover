package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 *
 * A Plateau is a square space table where rovers can land.
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
        if (this.busy(rover.x, rover.y))
            throw IllegalStateException("Cannot position rover on top of other one.")
        updateRoverPosition(rover)
        this.rovers.add(rover)
        rover.plateau = this
    }

    /**
     * Change rover position. If it's on plateau, removes it first
     * If after position change is on plateau, adds it back.
     */
    private fun updateRoverPosition(rover: Rover, x: Int = rover.x, y: Int = rover.y) {
        if (this.isRoverOnPlateau(rover))
            this.matrix[rover.x].remove(rover)
        this.matrix[x].add(y, rover)
    }

    /**
     * @return true if rover is within this bounderies
     */
    fun isRoverOnPlateau(rover: Rover): Boolean =
            isOnPlateau(rover.x, rover.y)

    /**
     * @return true if coordinates (x,y) are in the plateau bounderies
     */
    fun isOnPlateau(x: Int, y: Int) = 0 <= x && x < this.maxX && 0 <= y && y < this.maxY

    /**
     * Tells the rover to move
     */
    fun move(roverId: Int, instructionsString: String) {
        findRoverById(roverId).move(instructionsString)
    }

    /**
     * Moves on the right direction
     * If the position is out of the Plateau bounderies, it get's ignored
     */
    fun move(rover: Rover, newX: Int, newY: Int) {
        if (this.isOnPlateau(newX, newY)) {
            val nextCell = this.matrix[newX][newY]
            if (nextCell == null) { // ok to move
                this.updateRoverPosition(rover, newX, newY)
                println(String.format("%s moved to (%s,%s)", rover, newX, newY))
            } else {
                println("Avoiding rover collision..")
            }
        } else
            println("Ignoring out of plateau rover coordinates..")
    }

    /**
     * Id is associated with position
     */
    private fun findRoverById(roverId: Int) = this.rovers[roverId - 1]

    fun isValid(): Boolean {
        return this.maxX > 0 && this.maxY > 0
    }

    /**
     * @return true if that position is occupied
     */
    fun busy(x: Int, y: Int): Boolean {
        var busy = false
        try {
            busy = this.matrix[x][y] != null
        } catch (e: Exception) {
            // do nth
        }
        return busy
    }

    companion object {
        val ILLEGAL_DIMENSIONS = "Plateau cannot have 0 or negative dimensions"
    }
}
