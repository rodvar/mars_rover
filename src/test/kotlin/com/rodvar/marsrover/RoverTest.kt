package com.rodvar.marsrover

import com.rodvar.marsrover.domain.Rover
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by rodvar on 20/12/17.
 */
class RoverTest {

    private lateinit var rover: Rover

    @Before
    fun setup() {
        this.rover = Rover(1, Rover.Orientation.N)
    }

    @Test
    fun testRotateRight() {
        this.rover.rotateRight()
        assertEquals(Rover.Orientation.E, this.rover.orientation)
        this.rover.rotateRight()
        assertEquals(Rover.Orientation.S, this.rover.orientation)
        this.rover.rotateRight()
        assertEquals(Rover.Orientation.W, this.rover.orientation)
        this.rover.rotateRight()
        assertEquals(Rover.Orientation.N, this.rover.orientation)
    }

    @Test
    fun testRotateLeft() {
        this.rover.rotateLeft()
        assertEquals(Rover.Orientation.W, this.rover.orientation)
        this.rover.rotateLeft()
        assertEquals(Rover.Orientation.S, this.rover.orientation)
        this.rover.rotateLeft()
        assertEquals(Rover.Orientation.E, this.rover.orientation)
        this.rover.rotateLeft()
        assertEquals(Rover.Orientation.N, this.rover.orientation)
    }

    @Test(expected = IllegalStateException::class)
    fun testCantMoveRoverWithoutPlateau() {
        this.rover.move("M")
    }

}