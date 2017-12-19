package com.rodvar.marsrover.domain

/**
 * Created by rodvar on 19/12/17.
 */
data class Plateau(val maxX: Int, val maxY: Int) {

    lateinit var rovers: List<Rover>
    val matrix: MutableList<MutableList<Rover?>> = mutableListOf()

    init {
        for (x in 0..maxX - 1) {
            matrix.add(mutableListOf())
            for (y in 0..maxY - 1)
                matrix.get(x).add(null)
        }
    }

    fun position(rover: Rover, x: Int, y: Int) {
        this.matrix.get(x).add(y, rover)
    }
}