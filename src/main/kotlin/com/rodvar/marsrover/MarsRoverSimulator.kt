package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Plateau
import com.rodvar.marsrover.parsing.RoversPresenter
import com.rodvar.marsrover.parsing.SimulationInstructionsParser
import java.io.File
import java.io.InputStream


/**
 * Created by rodvar on 19/12/17.
 *
 * See README.md file for details
 */
class MarsRoverSimulator {


    /**
     * Executes the simulation based on the inputData string, returning the results as a striong as well
     */
    fun execute(inputData: String): String {
        var output: String
        try {
            println("Received input: " + inputData)

            val simulationInstructionsReader = SimulationInstructionsParser(inputData)
            val plateau = simulationInstructionsReader.parse()

            println(String.format("Dimensions (%s,%s)", plateau.maxX, plateau.maxY))
            simulationInstructionsReader.positioning(plateau)
            executeMovements(plateau, simulationInstructionsReader)

            output = RoversPresenter(plateau.rovers).toString()
        } catch (e: Exception) {
            output = e.localizedMessage
        }
        return output
    }

    private fun executeMovements(plateau: Plateau, simulationInstructionsReader: SimulationInstructionsParser) {
        println("Executing movements.. " + simulationInstructionsReader.roversQuantity)
        for (i in 1..simulationInstructionsReader.roversQuantity)
            plateau.move(i, simulationInstructionsReader.instructionsFor(i))
    }


    companion object {

        @JvmStatic
        private fun help(): String {
            return "Welcome to Mars-Rover simulator\n\n" +
                    "To run a file execute this program like this:\n" +
                    "  $ java -jar build/lib/mars_rover.jar -f test.txt\n\n" +
                    "Where text.txt is your test file\n" +
                    "A test file is already provided, and a script that builds and runs it as well. Simple go:\n" +
                    " $./run_test.sh"
        }

        @JvmStatic
        fun main(args: Array<String>) {
            when {
                args.isEmpty() -> println(help())
                args[0] == "-f" -> {
                    try {
                        val inputStream: InputStream = File(args[1]).inputStream()
                        val inputString = inputStream.bufferedReader().use { it.readText() }
                        println(MarsRoverSimulator().execute(inputString))
                    } catch (e: Exception) {
                        println("Failed to parse file: " + e.localizedMessage)
                    }
                }
                else -> println(MarsRoverSimulator().execute(args[0]))
            }
        }
    }
}
