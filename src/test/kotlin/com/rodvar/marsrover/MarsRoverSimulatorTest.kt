package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Plateau.Companion.ILLEGAL_DIMENSIONS
import com.rodvar.marsrover.parsing.RoversPresenter
import com.rodvar.marsrover.parsing.SimulationInstructionsParser.Companion.NO_PLATEAU_PROVIDED
import org.junit.Test
import kotlin.test.assertEquals

class MarsRoverSimulatorTest {

    private val BASIC_TEST_INPUT = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"
    private val BASIC_TEST_EXPECTED = "1 3 N\n5 1 E"

    private val SUPPORTS_BLANK_LINES = "\n5 5\n\n\n1 2 N\n\nLLLRRRM\n\n"
    private val SUPPORTS_BLANK_LINES_EXPECTED = "1 3 N"

    private val SUPPORTS_BLANK_LINES2 = "\n10 10\n\n\n1 2 N\n\nLLLRRRM\n\n1 5 S\n\nMMRR"
    private val SUPPORTS_BLANK_LINES2_EXPECTED = "1 3 N\n1 3 N"

    private val ON_TOP_OF_OTHERS = "10 10\n4 4 N\nMR\n4 4 N\nMMRMRMRMMM"
    private val ON_TOP_OF_OTHERS_EXPECTED = "4 5 E"

    @Test
    fun testBasicProvidedDataInput() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(BASIC_TEST_INPUT))
    }

    @Test
    fun testRoverPlacedOnTopOfOtherGetsIgnored() {
        assertEquals(ON_TOP_OF_OTHERS_EXPECTED, MarsRoverSimulator().execute(ON_TOP_OF_OTHERS))
    }

    @Test
    fun testSupportsBlankLines() {
        assertEquals(SUPPORTS_BLANK_LINES_EXPECTED, MarsRoverSimulator().execute(SUPPORTS_BLANK_LINES))
    }

    @Test
    fun testSupportsBlanksLines2() {
        assertEquals(SUPPORTS_BLANK_LINES2_EXPECTED, MarsRoverSimulator().execute(SUPPORTS_BLANK_LINES2))
    }

    @Test
    fun testValidDimensions() {
        assertEquals(ILLEGAL_DIMENSIONS, MarsRoverSimulator().execute("   100     -54   "))
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
        assertEquals(NO_PLATEAU_PROVIDED, MarsRoverSimulator().execute("A 1"))
    }

    @Test
    fun testRoverOutOfPlateau () {
        assertEquals(RoversPresenter.NO_ROVERS, MarsRoverSimulator().execute("5 5\n" +
                "7 2 N\n" +
                "LMLMLMLMM"))
    }

    
    @Test
    fun testRoverMovingOutOfPlateau () {
        assertEquals("0 -1 S", MarsRoverSimulator().execute("5 5\n" +
                "0 0 S\n" +
                "M"), "Should admit rover stepping off the plateau")
    }
}
