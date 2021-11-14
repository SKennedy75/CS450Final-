HW3 README Sean Kennedy

To use my enhanced version, you need 3 windows of BlueJ. You first run the PS class and enter a 
port number into the args[] prompt. Then, you need to open 2 PC classes and enter "localhost", "(portnumber)"
into the args[] commands. From there, you will see two windows open for the project. You take turns entering 
information into them when the server commands you to do so. You need to control the draft for both players
and it will take turns starting with player one. After the draft is over, you have a period to talk to
the other player or simply just move on by typing "adv". Once the post draft phase is over, the game 
will be simulated via the synchronization feature and the winner will be determined. After the winner is
displayed, you can decide if you want to play again or not. If you want to play again, keep in mind that both 
players have to say "y" for that to happen. If at least one says "n", the program will not restart. Before
exiting, the game will relay the amount of wins for each team and the total number of ties.

The features I have implemented thus far are the ability to choose your team name, the randomized selection
of 3 arrays that each contain 10 different names for draft diversity, the ability to talk to the other player
as much as you want after the draft is over, the accumulation of wins, losses and ties, an overtime
feature for rare tied games, and a playerList class that has many helpful methods for the draft phase
in general. My synchronization feature was for simulating the score for both teams. It simulates
both seperate scores simultaneously one quarter at a time to return a randomized total for both teamScore 
and enemyScore(meaning player one score and player two score, respectively). It spans across three 
small classes (four with main) and uses many of the features that were seen in Lab 5. I had println
calls in the synchronized method to show that the synchronization is properly working.

The test cases include a full run through the program once, one with an attempt to enter an int value
outside the possible range in the draft, one showing that the Arrays are properly reset upon restarting,
and finally the final score being displayed after saying "n" to play again.