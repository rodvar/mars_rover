package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Plateau
import com.rodvar.marsrover.parsing.RoversPresenter
import com.rodvar.marsrover.parsing.SimulationInstructionsParser

/**
 * Created by rodvar on 19/12/17.
 */
class MarsRoverSimulator {


    /**
     * Executes the simulation based on the inputData string, returning the results as a striong as well
     */
    fun execute(inputData: String): String {
        println("Received input: " + inputData)

        val simulationInstructionsReader = SimulationInstructionsParser(inputData)
        simulationInstructionsReader.parse()

        val plateau = simulationInstructionsReader.init()
        println(String.format("(%s,%s)", plateau.maxX, plateau.maxY))

        simulationInstructionsReader.positioning(plateau)
        executeMovements(plateau, simulationInstructionsReader)

        return RoversPresenter(plateau.rovers).toString()
    }

    private fun executeMovements(plateau: Plateau, simulationInstructionsReader: SimulationInstructionsParser) {

    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(MarsRoverSimulator().execute(args[0]))
        }
    }
}