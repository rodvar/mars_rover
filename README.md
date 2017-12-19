# mars_rover

It's a square plateau rover landing simulator.

Given the rover dimentions and the set of rovers + movement instructions, it will determine the final position of each rover.

The following assumptions has been made

 - If the given dimentions are invalid (not real), the simulation aborts with an error
 - If a given set of instructions for a rover indicates it's initial position outside of the rover boundaries, that set of instructions is ignored and the label IGNORED - INVALID POSITION will be displayed as output
 - If a given set of instructions for a rover involves a collition with an already landed one, that rover will be considered unusable from that point, informing the crash position as it's final position and a CRASHED label
 - If a given set of instructions for a rover places it outside the rover at anytime, it's considered lost and a label will be shown next to the last know position: LOST 
