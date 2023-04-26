# Laboratorul nr. 7

## Compulsory

- Create an object oriented model of the problem. (*solved*)
- Each robot will have a name and they must perform in a concurrent manner, moving randomly around the map and extracting tokens from the shared memory when reaching an unvisited cell. A message will be displayed on the screen every time a robot visits a new cell. (*solved*)
- __Simulate the exploration using a thread for each robot.__  Pay attention to the synchronization of the threads when extracting tokens and when visiting cells. (*solved*)

## Homework


- Implement the commands that start/pause the robots (all of them or only a specific one). A robot can be paused for a specific time or indefinitely, requiring a start command. The commands must be given using the keyboard. (*kinda solved*)
- Design an algorithm such that each robots will try to explore the map in a systematic fashion, ensuring the termination of the exploration process. (*solved*)
- Implement a timekeeper thread that runs concurrently with the player threads, as a daemon. This thread will display the running time of the exploration and it will stop it exceeds a certain time limit. (*solved*)
- At the end of the exploration, determine how many tokens each robot has placed in the matrix. (*solved*)
