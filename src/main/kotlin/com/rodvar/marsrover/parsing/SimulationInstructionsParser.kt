package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Plateau
import com.rodvar.marsrover.domain.Rover

/**
 * Created by rodvar on 19/12/17.
 */
class SimulationInstructionsParser(val inputData: String) {

    lateinit var instructionLines: List<String>
    var roversQuantity: Int = 0

    fun parse() {
        instructionLines = inputData.split("\n")
        roversQuantity = (instructionLines.size - 1) / 2
    }

    @Throws(ParsingException::class)
    fun init(): Plateau {
        try {
            return Plateau(instructionLines[0].split(" ")[0].toInt(), instructionLines[0].split(" ")[1].toInt())
        } catch (e: NumberFormatException) {
            throw ParsingException(INVALID_DIMENSIONS, e)
        }
    }

    fun positioning(plateau: Plateau) {
        println("Positioning..")
        var id = 1
        instructionLines.filter { it -> instructionLines.indexOf(it) % 2 != 0 && instructionLines.indexOf(it) > 0 }
                .map { it ->
                    try {
                        plateau.position(
                                Rover(id++, Rover.Orientation.valueOf(it[it.length - 1].toString())),
                                it[0].toString().toInt(),
                                it[2].toString().toInt())
                    } catch (e : IllegalArgumentException) {
                        println("Ignoring rover because of invalid position")
                    }
                }
    }

    fun instructionsFor(roverId: Int) =
            instructionLines.filter { it -> instructionLines.indexOf(it) % 2 == 0 && instructionLines.indexOf(it) > 0 }[roverId - 1]


    /**
     * Exceptions definition
     */
    class ParsingException(s: String, e: NumberFormatException) : Exception(s, e)

    companion object {
        val INVALID_DIMENSIONS = "Invalid dimensions provided"
    }
}