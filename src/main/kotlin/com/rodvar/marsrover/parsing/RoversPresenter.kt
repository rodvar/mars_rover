package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Rover

/**
 * Created by rodvar on 19/12/17.
 *
 * Prepares output to be presented based on the passed list of rovers
 */
class RoversPresenter(val rovers: List<Rover>) {

    companion object {
        val NO_ROVERS_LANDED = "No Rovers Landed on the Plateau"
        val NO_ROVERS = "No Rovers Provided"
    }

    /**
     * Presents only the rovers that landed.
     *
     * Rovers that didn't landed on the plateau are ignored
     *
     * If no rovers were passed, a message will indicate so in the output
     * If no rovers landed on the plateau, a message will indicate so
     *
     */
    override fun toString(): String {
        var output = ""

        if (rovers.isEmpty())
            output = NO_ROVERS
        else {
            rovers.map { it ->
                output += "\n" + it.x.toString() + " " + it.y.toString() + " " + it.orientation.toString()
            }
            output = output.substring(1)
        }
        return output
    }
}