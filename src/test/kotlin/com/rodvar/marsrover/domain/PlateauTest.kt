package com.rodvar.marsrover.domain

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by rodvar on 22/12/17.
 */
class PlateauTest {

    private lateinit var plateau: Plateau

    @Before
    fun setup() {
        this.plateau = Plateau(5, 5)
    }

    @Test
    fun testRoverInPlateau() {
        assertTrue(this.plateau.isRoverOnPlateau(Rover(1, Rover.Orientation.N, 1, 1)))
    }

    @Test
    fun testRoverNotInPlateau() {
        assertFalse(this.plateau.isRoverOnPlateau(Rover(1, Rover.Orientation.N, -1, 1)))
    }

    @Test
    fun testPositionAddsRoverToPlateau() {
        val rover = Rover(1, Rover.Orientation.N, 1, 1)
        this.plateau.position(rover)
        assertEquals(rover, this.plateau.rovers[0])
        assertEquals(rover.plateau, this.plateau)
    }
}