package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Plateau
import com.rodvar.marsrover.domain.Rover

/**
 * Created by rodvar on 19/12/17.
 */
class SimulationInstructionsParser(val inputData: String) {

    lateinit var instructionLines: List<String>

    fun parse() {
        instructionLines = inputData.split("\n")
    }

    fun init() =
            Plateau(instructionLines[0].split(" ")[0].toInt(), instructionLines[0].split(" ")[1].toInt())

    fun positioning(plateau: Plateau) {
        var id = 1
        instructionLines.filter { it -> instructionLines.indexOf(it) % 2 != 0 && instructionLines.indexOf(it) > 0 }
                .map { it -> plateau.position(Rover(id++, it[it.length - 1]), it[0].toString().toInt(), it[2].toString().toInt()) }
//        for (i in instructionLines.indices) {
//            if (i > 0 && i % 2 != 0) {
//                println(instructionLines[i])
//            }
//        }
    }
}