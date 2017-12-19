package com.rodvar.marsrover

import com.rodvar.marsrover.parsing.RoversPresenter
import org.junit.Test
import kotlin.test.assertEquals

class MarsRoverSimulatorTest {

    val BASIC_TEST_INPUT = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"
    val BASIC_TEST_EXPECTED = "1 3 N\n5 1 E"

    @Test
    fun testBasicProvidedDataSet() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(BASIC_TEST_INPUT))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalDimentions() {
        MarsRoverSimulator().execute("-1 0")
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
