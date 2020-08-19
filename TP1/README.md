# Sokoban solver

## Summary

an implementation of a general problem solver, that is, an algorithm that given a problem which satisfies the interfaces defined in the interfaces module will always find a solution if it exists and the problem is correctly modelled.

The program can change the algorithm used based on user input. The possible algorithms are: BFS, DFS, IDDFS, A*, GREEDY and IDA*.

## Running the project

running sok.sh will compile, package and run the project.

sok.sh should be called as such: ./sok.sh <path-to-board> <algorithm> <heuristic>

path-to-board: the path to the .sok plain text file with a valid sokoban board
algorithm: the algorithm to use. Options: BFS|DFS|IDDFS|ASTAR|GREEDY|IDASTAR
heuristic: an optional argument representing the heuristic that wants to be used in case an informed search is being used. Options: place|distance|path.

## Examples

./sok.sh problem/boards/informatika01.sok BFS
./sok.sh problem/boards/informatika01.sok ASTAR place

## Heuristics

place: The count of all blocks that aren't at a goal
distance: The sum of the distance of all blocks to their closest goal
path: The length of the path to be traversed to get to the closest goal. Taking into account only walls (and not other blocks).
