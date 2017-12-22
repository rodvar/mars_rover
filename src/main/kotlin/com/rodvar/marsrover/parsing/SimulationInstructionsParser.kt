package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Plateau
import com.rodvar.marsrover.domain.Rover
import java.util.regex.Pattern

/**
 * Created by rodvar on 19/12/17.
 *
 * Responsible for parsing the input data string. Can recognise the Plateau, the Rovers and the movement instructions
 */
class SimulationInstructionsParser(val inputData: String) {

    lateinit var inputDataLines: List<String>
    var roversQuantity: Int = 0

    // It is expected that the file starts with blank spaces, or the plateau definition
    private val PLATEAU_PATTERN = Pattern.compile("^\n* *-?\\d+ +-?\\d+.*")
    // Pattern to analise a line to see if it's a rover definition
    private val ROVER_POSITION_LINE_PATTERN = Pattern.compile(" *-?\\d+ +-?\\d+ +(N|E|W|S) *")
    // Pattern to analise a line to see if it's a rover instructions set
    private val ROVER_INSTRUCTIONS_LINE_PATTERN = Pattern.compile(" *(L|R|M) *")

    /**
     *
     */
    fun parse(): Plateau {
        inputDataLines = inputData.split("\n")
        val plateau = this.generatePlateau()
        plateau ?: throw IllegalArgumentException(NO_PLATEAU_PROVIDED)
        return plateau
    }

    @Throws(ParsingException::class)
    fun generatePlateau(): Plateau? {
        try {
            val matcher = PLATEAU_PATTERN.matcher(inputData)
            var plateauData: List<String> = listOf()
            if (matcher.find())
                plateauData = matcher.group().toString().trim().split(Regex(" +"))
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
        inputDataLines.filter { it ->
            !it.isBlank()
        }.map { it ->
                    try {
                        val matcher = ROVER_POSITION_LINE_PATTERN.matcher(it)
                        if (matcher.find()) {
                            val roverData = matcher.group().toString().trim().split(Regex(" +"))
                            val rover = Rover(++roversQuantity, Rover.Orientation.valueOf(roverData[2]), roverData[0].toInt(), roverData[1].toInt())
                            plateau.position(rover)
                        }
                    } catch (e: IllegalArgumentException) {
                        roversQuantity--
                        println("Ignoring rover because of invalid position:\n" +
                                " " + it)
                    }
                }
        println(roversQuantity.toString() + " rovers positioned")
    }

    fun instructionsFor(roverId: Int): String =
            inputDataLines.filter { it ->
                !it.isBlank() && ROVER_INSTRUCTIONS_LINE_PATTERN.matcher(it).find()
            }[roverId - 1].trim()


    /**
     * Exceptions definition
     */
    class ParsingException(s: String, e: NumberFormatException) : Exception(s, e)

    companion object {
        val INVALID_DIMENSIONS = "Invalid dimensions provided"
        val NO_PLATEAU_PROVIDED = "No plateau provided"
    }
}