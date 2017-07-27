### Assignment 6 - Rearranging cars
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot.

The parking lot is described by an array of numbers. Let's identify cars with numbers from 1 to N-1, and the number 0 means an empty parking space.

The input to your function is two arrays, each with a permutation of the numbers 0 to N (you don't have to validate it). Your function must generate a series of moves and print them.


## Solution:    
The algorithm for finding minimal moves is implemented in the method "getReorderMoves(int[], int[])" within the "Parking" class.  
The pseudocode is as follows:  
Input: initial array and target array.  
1. Create a [car -> position] mapping between the misplaced elements. The mapping also contains [0->position] mapping for the empty spot, regardless of the fact if it is on its correct position or not.  
2. While the map has more than one element, do:  
     a) Get the position of the empty spot (0) from the mapping (created in Step 1), and store as "emptySpotPos".  
     b) Get the car number which is parked in the position "emptySpotPos" of the target array, and store as "targetCar".  
     c) Get the position of the "targetCar" in the initial array, and store as "currentPos".  
     d) Remove empty spot (0) from the mapping.    
     e) If "targetCar" == 0, then, choose any position from the mapping of the misplaced elements, and also update the "targetCar" and "currentPos" accordingly. Add [targetCar -> emptySpotPos] to the mapping of the misplaced elements. Go to Step 2g.  
     f) Otherwise, "emptySpotPos" is the correct position of the "targetCar", and hence remove "targetCar" from the mapping.  
     g) To update the new position of the empty spot in the mapping, add [0 -> currentPos] to the mapping.  
     h) Print: move from "currentPos" to "emptySpotPos".  
     

