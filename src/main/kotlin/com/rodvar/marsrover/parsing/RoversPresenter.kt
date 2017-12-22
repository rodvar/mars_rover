package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Rover

/**
 * Created by rodvar on 19/12/17.
 */
class RoversPresenter(val rovers: List<Rover>) {

    companion object {
        val NO_ROVERS = "No rovers provided"
    }

    override fun toString(): String {
        var output = NO_ROVERS
        if (rovers.size > 0) {
            output = ""
            for (rover in rovers) {
                output += rover.x.toString() + " " + rover.y.toString() + " " + rover.orientation.toString() + "\n"
            }
            output = output.substring(0, output.length - 1)
        }
        return output
    }
}