package com.rodvar.marsrover.parsing

import com.rodvar.marsrover.domain.Rover

/**
 * Created by rodvar on 19/12/17.
 */
class RoversPresenter(val rovers: List<Rover>) {
    override fun toString(): String {
        var output = ""
        for (rover in rovers) {
            output += rover.lastX.toString() + " " + rover.lastY.toString() + " " + rover.orientation.toString() + "\n"
        }
        return output.substring(0, output.length - 1)
    }
}