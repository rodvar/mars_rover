package com.rodvar.marsrover

import org.junit.Test
import kotlin.test.assertEquals

class MarsRoverSimulatorTest {

    val BASIC_TEST_INPUT = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"
    val BASIC_TEST_EXPECTED = "1 3 N\n5 1 E"

    @Test
    fun testBasicProvidedDataSet() {
        assertEquals(BASIC_TEST_EXPECTED, MarsRoverSimulator().execute(BASIC_TEST_INPUT))
    }
}