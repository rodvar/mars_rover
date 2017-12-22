package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Plateau.Companion.ILLEGAL_DIMENSIONS
import com.rodvar.marsrover.parsing.RoversPresenter
import com.rodvar.marsrover.parsing.SimulationInstructionsParser.Companion.INVALID_DIMENSIONS
import org.junit.Test
import kotlin.test.assertEquals

class MarsRoverSimulatorTest {

    private val BASIC_TEST_INPUT = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"
    private val BASIC_TEST_EXPECTED = "1 3 N\n5 1 E"

    @Test
    fun testBasicProvidedDataSet() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(BASIC_TEST_INPUT))
    }

    @Test
    fun testIllegalDimensions1() {
        assertEquals(ILLEGAL_DIMENSIONS, MarsRoverSimulator().execute("3 -1"))
    }

    @Test
    fun testIllegalDimensions2() {
        assertEquals(ILLEGAL_DIMENSIONS, MarsRoverSimulator().execute("0 3"))
    }

    @Test
    fun testCannotRunWithNonNumericDimensions() {
        assertEquals(INVALID_DIMENSIONS, MarsRoverSimulator().execute("A 1"))
    }

    @Test
    fun testRoverOutOfPlateau () {
        assertEquals(RoversPresenter.NO_ROVERS, MarsRoverSimulator().execute("5 5\n" +
                "7 2 N\n" +
                "LMLMLMLMM"))
    }

    
    @Test
    fun testRoverMovingOutOfPlateau () {
        assertEquals("0 0 S", MarsRoverSimulator().execute("5 5\n" +
                "0 0 S\n" +
                "M"))
    }
}
