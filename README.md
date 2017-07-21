### Assignment 6 - Rearranging cars
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot.

The parking lot is described by an array of numbers. Let's identify cars with numbers from 1 to N-1, and the number 0 means an empty parking space.

The input to your function is two arrays, each with a permutation of the numbers 0 to N (you don't have to validate it). Your function must generate a series of moves and print them.

For example, with N=4 and the inputs [1, 2, 0, 3] and [3, 1, 2, 0], the following output could be generated:
move from 0 to 2
move from 3 to 0
move from 1 to 3
move from 2 to 1
move from 3 to 2
