package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Plateau.Companion.ILLEGAL_DIMENSIONS
import com.rodvar.marsrover.parsing.RoversPresenter
import com.rodvar.marsrover.parsing.SimulationInstructionsParser.Companion.INVALID_DIMENSIONS
import org.junit.Test
import kotlin.test.assertEquals

class MarsRoverSimulatorTest {

    private val BASIC_TEST_INPUT = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"
    private val BASIC_TEST_EXPECTED = "1 3 N\n5 1 E"

    private val SUPPORTS_BLANK_LINES = "\n5 5\n\n\n1 2 N\n\nLMLMLMLMM\n\n3 3 E\n\nMMRMMRMRRM\n\n"

    @Test
    fun testBasicProvidedDataSet() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(BASIC_TEST_INPUT))
    }

    @Test
    fun testSupportsBlankLines() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(SUPPORTS_BLANK_LINES))
    }

    @Test
    fun testValidDimensions() {
        assertEquals(RoversPresenter.NO_ROVERS, MarsRoverSimulator().execute("   100     -54   "))
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
