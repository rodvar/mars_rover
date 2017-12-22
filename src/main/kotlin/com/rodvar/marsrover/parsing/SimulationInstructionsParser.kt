package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Plateau
import java.util.regex.Pattern

/**
 * Created by rodvar on 19/12/17.
 *
 * Responsible for parsing the input data string. Can recognise the Plateau, the Rovers and the movement instructions
 */
class SimulationInstructionsParser(val inputData: String) {

    var roversQuantity: Int = 0

    private val PLATEAU_PATTERN = Pattern.compile("^\n* *-?\\d+ +-?\\d+.*")
    private val ROVER_PATTERN = Pattern.compile("")

    /**
     *
     */
    fun parse(): Plateau? {
        val plateau = this.generatePlateau()
//        roversQuantity = (instructionLines.size - 1) / 2
        return plateau
    }

    @Throws(ParsingException::class)
    fun generatePlateau(): Plateau? {
        try {
            val matcher = PLATEAU_PATTERN.matcher(inputData)
            var plateauData: List<String> = listOf()
            if (matcher.find()) {
                plateauData = matcher.group().toString().trim().split(Regex(" +"))
            }
            return when {
                plateauData.isEmpty() -> null
                else -> Plateau(plateauData[0].toInt(), plateauData[1].toInt())
            }
        } catch (e: NumberFormatException) {
            throw ParsingException(INVALID_DIMENSIONS, e)
        }
    }

    fun positioning(plateau: Plateau) {
        println("Positioning..")
        var id = 1
//        instructionLines.filter { it -> instructionLines.indexOf(it) % 2 != 0 && instructionLines.indexOf(it) > 0 }
//                .map { it ->
//                    try {
//                        plateau.position(
//                                Rover(id++, Rover.Orientation.valueOf(it[it.length - 1].toString())),
//                                it[0].toString().toInt(),
//                                it[2].toString().toInt())
//                    } catch (e : IllegalArgumentException) {
//                        println("Ignoring rover because of invalid position")
//                    }
//                }
    }

    fun instructionsFor(roverId: Int) = ""
//            instructionLines.filter { it -> instructionLines.indexOf(it) % 2 == 0 && instructionLines.indexOf(it) > 0 }[roverId - 1]


    /**
     * Exceptions definition
     */
    class ParsingException(s: String, e: NumberFormatException) : Exception(s, e)

    companion object {
        val INVALID_DIMENSIONS = "Invalid dimensions provided"
    }
}