# Mars Rover Landing Simulator

It's a square plateau rover landing simulator.

Given the rover dimensions and the set of rovers + movement instructions, it will determine the final position of each rover.

## Version 1.0

# What's new ?

 - Plateau can be of any dimension, numbers could be anything from (Int.MIN, Int.MAX]
 - Much more input flexibility. Supports blank lines, separations, extra spaces in between numbers, etc
 - Validates positioning of rovers on top of each other
 - Rovers can fly in and out of the plateau. However, rovers can't be positioned out of the plateau on start.
 - CI during development, much robust version :)

# How to test it

 Simply run `$java -jar build/libs/mars_rover.jar` (no parameters) for instructions.
 
 Or you could run the provided test.txt sample input file with the bash script:
 
  - `./run_test.sh`
  
 This builds (running all the test cases) and then runs with the input file. Enjoy!

## Version 0.1

The following assumptions has been made

 - Provided plateau dimensions need to satisfy (x,y) where x > 0 && y > 0
 - If a given set of instructions for a rover indicates it's initial position outside of 
 the rover boundaries, that rover's set of instructions is ignored
 - If a given set of instructions for a rover involves a collision with another rover,  that will ignore that movement
 - Movements that would put the rover outside a plateau will be ignored
 - It'a assumed that any test data input will respect the format provided on the example, no extra spaces
 between characters, or blank lines
 - In this version, plateau max dimensions are [0,0] - [9,9]
 - If the input does not bring any rover position data, a legend "No rovers provided" is gonna be shown as a result
 

To test this code you could either:

 - read the project with any IDE that support gradle and run it.
 - run on command line:
   - $gradle clean build
   - $java -jar build/libs/mars_rover.jar "<YOUR_INPUT>"
 - build through command line as explained above and then open file://<YOUR_PATH>/mars_rover/build/reports/tests/test/index.html 
 to see the test results
 
 Any questions or improvements? http://github.com/rodvar 
 Thanks!
 Rodrigo
