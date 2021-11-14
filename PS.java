import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
/**
 * PickupServer Class
 *
 * @author (Sean Kennedy)
 * @version (Assignment 3 - Enhanced Version)
 */
public class PS {
   public static void main(String[] args) throws IOException {
   //Beginning state to introduce game
   int INTRO = 0;
    
   //Create two states for the draft phase, for waiting and drafting
   int DRAFTING = 1;
   int WAITING = 2;
    
   //Another state for everything after
   int POST_DRAFT = 3;
    
   //State to prompt to play again
   int POST_GAME = 4;
    
   //Index int variable for draft selections
   int index = 0;
    
   //Variables that will decide game winners and keep track of total wins/ties
   int teamScore = 0;
   int enemyScore = 0;
   int teamWins = 0;
   int enemyWins = 0;
   int ties = 0;
    
   //PlayerList objects that are used for the draft
   PlayerList pl = new PlayerList();
   PlayerList pl2 = new PlayerList();
    
   //Initial list of players available to draft
   ArrayList<String> nameList = pl.chooseArrayList();
    
   //Initial team and enemy lists, teamList is player one and
   //enemyList is player two
   ArrayList<String> teamList = new ArrayList<String>(5);
   ArrayList<String> enemyList = new ArrayList<String>(5);   
        
   //Random and Scanner
   Random rnd = new Random();
    
   //Initializations for teamName variables for player one and two
   String teamName = "", teamName2 = "";
    
   //Intial state
   int state = INTRO;
    
   //Read in the port number given by the user
   int portNumber = Integer.parseInt(args[0]);
    
   //Server and client sockets
   ServerSocket serverSocket1;
   Socket clientSocket1, clientSocket2;
    
   //Connect server socket to args portNumber
   serverSocket1 = new ServerSocket(portNumber);
    
   //Wait for two client connections to start the game
   clientSocket1 = serverSocket1.accept();
   clientSocket2 = serverSocket1.accept();
    
   //PrintWriter and BufferedReader for player one
   PrintWriter out =
            new PrintWriter(clientSocket1.getOutputStream(), true);
   BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket1.getInputStream()));
   
   //PrintWriter and BufferedReader for player two
   PrintWriter out2 =
            new PrintWriter(clientSocket2.getOutputStream(), true);
    BufferedReader in2 = new BufferedReader(
            new InputStreamReader(clientSocket2.getInputStream()));
   
   //Let the connected players know if they are player one or two         
   out.println("You are player one");
   out2.println("You are player two");
   
   //Let the players know the game is starting
   out.println("Both players have connected. Beginning game.");
   out2.println("Both players have connected. Beginning game.");
   
   //Boolean that will close the game when finished
   boolean work = true;    
   while (work = true) { 
   if (state == INTRO) {
       if (teamName.equals("") || (teamName2.equals(""))) {
           //Welcome the players to the game and prompt for team names
           out.println("Welcome to Pickup Basketball. Please enter " 
               + "a team name:");
           out2.println("Welcome to Pickup Basketball. Please enter " 
               + "a team name:");
           
           //Read in both team names    
           teamName = in.readLine();
           teamName2 = in2.readLine();
           
           //Confirm the team names and let them know what the other
           //player's team name is
           out.println("Team name confirmed as: " + teamName 
               + ". Your opponent is: " + teamName2 + ". Beginning draft.");
           out2.println("Team name confirmed as: " + teamName2 
               + ". Beginning draft. Your opponent " + teamName + 
               " is selecting first.");
           
           //Set state to DRAFTING (player one has first pick)    
           state = DRAFTING;
       }
       else {
           //On replays of the game you do not need to enter team name again
           out.println("Good luck!");
           out2.println("Good luck!");
           state = DRAFTING;
       }
   }
   
   if (state == DRAFTING) {
       //Update the nameList for synchronization purposes
       nameList = pl.update(nameList);
       if(nameList.size() > 0) {
           //Get the current size of the list of names
           int size = nameList.size() - 1;
           
           //Display the current available players to player one and 
           //tell them to select
           out.println("Available Players: " + nameList);
           out.println("Use ints 0 to " 
                + size + " to select your player");
           out.println("It is your turn to select"); 
           
           //Tell player two that they have to wait for player one's selection
           out2.println("Wait for " + teamName + " to make their selection.");
           
           //Read in the selection and convert it to integer with the parseInt fucntion
           String cMessage = in.readLine();
           index = Integer.parseInt(cMessage);
           
           //Ensure the input entered is valid
           while (index < 0 || index > nameList.size()-1) {
               out.println("Invalid selection (Use ints 0 to " 
                + size + " to select your player)");
               out.println("It is your turn to select");
               cMessage = in.readLine();
               index = Integer.parseInt(cMessage);
           }
           
           //Let both players know the selection
           out.println(teamName + " have selected: " 
                    + pl.selected(nameList, index));
           out2.println(teamName + " have selected: " 
                    + pl2.selected(nameList, index));
           
           //Use the synchronous draft method to remove the name from the 
           //available players list and add it to the team list         
           pl.draft(nameList, teamList, index);
           
           //Update the nameList again for synchronization purposes
           nameList = pl.update(nameList);
           
           //Let player one know their current roster of selections
           out.println("Your team so far is: " + teamList);   
                                                  
           //Switch turns with player two
           state = WAITING; 
       }
       else{
            //If the draft is over, move to the post draft state
            out.println("The draft has concluded. Good luck!");
            out2.println("The draft has concluded. Good luck!");
            state = POST_DRAFT;
       }
   }
   
   if (state == WAITING) {
        //Player two is now selecting
        nameList = pl.update(nameList);
        if(nameList.size() > 0) {
           //The selection works the same as player one's, just with things
           //now going to out2
           int size = nameList.size() - 1;
           
           out2.println("Available Players: " + nameList);
           out2.println("Use ints 0 to " 
                + size + " to select your player");
           out2.println("It is your turn to select"); 
           
           //Tell player one to wait for player two's selection
           out.println("Wait for " + teamName2 + " to make their selection.");
           
           String cMessage = in2.readLine();
           index = Integer.parseInt(cMessage);
           
           //Esnure the input entered is valid
           while (index < 0 || index > nameList.size()-1) {
               out2.println("Invalid selection (Use ints 0 to " 
                + size + " to select your player)");
               out2.println("It is your turn to select");
               cMessage = in2.readLine();
               index = Integer.parseInt(cMessage);
           }
           
           //Let the players know of the selection
           out.println(teamName2 + " have selected: " 
                    + pl.selected(nameList, index));
           out2.println(teamName2 + " have selected: " 
                    + pl2.selected(nameList, index));
           
           //Use the other PlayerList object to ensure the draft synchronization
           //works
           pl2.draft(nameList, enemyList, index);
           
           //Let player two know of the selections they have made so far         
           out2.println("Your team so far is: " + enemyList);         
                    
                
           //Switch turns with player one
           state = DRAFTING; 
         }
       else{
            //If the draft is over, go to post draft phase
            out.println("The draft has concluded. Good luck!");
            out2.println("The draft has concluded. Good luck!");
            state = POST_DRAFT;
       }
   }
   
   if (state == POST_DRAFT) {  
    //Display the final rosters and let each player know which is theirs
    //just in case the do not remember their team name
    out.println("Final rosters: " + '\n' + teamName + " (you): "
        + teamList + '\n' + teamName2 + ": " + enemyList); 
    out2.println("Final rosters: " + '\n' + teamName2 + " (you): "
        + enemyList + '\n' + teamName + ": " + teamList);
    
    //Two empty String variables to use for conversation
    String p1 = "";
    String p2 = "";
    
    //Allow the players to talk before the game begins
    while(!p1.equals("adv") && (!p2.equals("adv"))) {  
         out.println("You can type to your opponent before the game begins " +
         "(type 'adv' to advance)");
         out2.println("You can type to your opponent before the game begins " +
         "(type 'adv' to advance)");
         
         //Read in messages from the players
         p1 = in.readLine();
         p2 = in2.readLine();
         
         //Display the messages, as long as they are not "adv"
         out.println(teamName2 + ": " + p2);
         out2.println(teamName + ": " + p1);
    }      
    
    //Once at least one player is ready to move on, let them know
    //the talking phase is over
    out.println("Advancement confirmed. Simulating result of game");
    out2.println("Advancement confirmed. Simulating result of game");
                
    /*
    Simulates a number between 40 and 66 for both team's scores
    The assumption is games are 4m quarters so that is a realistic
    Range for potential score
    */
   
    //Synchronization feature   
    SimGame sg = new SimGame();
    tOneSim t1 = new tOneSim(sg, teamScore);
    tTwoSim t2 = new tTwoSim(sg, enemyScore);
    
    Thread thread1 = new Thread(t1);
    Thread thread2 = new Thread(t2);
    
    //Start the threads that will call simScore on teamScore and 
    //enemyScore
    thread1.start();
    thread2.start();

    try
     {
       thread1.join();
       teamScore = t1.score;
       thread2.join();
       enemyScore = t2.score;
     }
     catch (InterruptedException e)
     {
       e.printStackTrace();
     }

    //Use the generated scores to determine the winner
    if (teamScore > enemyScore) {
           out.println("The final score was " +teamScore 
                + " to " + enemyScore + ". " + teamName + " won!");
           out2.println("The final score was " +teamScore 
                + " to " + enemyScore + ". " + teamName2 + " lost!");    
           //Increment team wins if you won the game
           teamWins++;
    } 
    else if (enemyScore > teamScore) {
            out.println("The final score was " + enemyScore 
                + " to " + teamScore + ". " + teamName + " lost!");
            out2.println("The final score was " + enemyScore 
                + " to " + teamScore + ". " + teamName2 + " won!");   
            //Increment enemyWins if computer won game
            enemyWins++;
    }
    else {
            //If score is tied, choose another random to simulate overtime
            out.println("The score is tied at " + teamScore + ". Going to overtime.");
            out2.println("The score is tied at " + teamScore + ". Going to overtime.");
            
            teamScore = teamScore + rnd.nextInt(16);
            enemyScore = enemyScore + rnd.nextInt(16);
             if (teamScore > enemyScore) {
                out.println("Overtime concluded. The final score was " 
                   + teamScore + " to " + enemyScore + ". " + teamName + " won!");
                out2.println("Overtime concluded. The final score was " 
                   + teamScore + " to " + enemyScore + ". " + teamName2 + " lost!"); 
                   
                teamWins++;
                }
             else if (enemyScore > teamScore) {
                out.println("Overtime concluded. The final score was " 
                  + enemyScore + " to " + teamScore + ". " + teamName + " lost!");
                out2.println("Overtime concluded. The final score was " 
                  + enemyScore + " to " + teamScore + ". " + teamName2 + " won!"); 
                  
                enemyWins++;
             } 
             else {
               out.println("The score after overtime is "
                 + teamScore + " to " + enemyScore + ". It's a tie!");
               out2.println("The score after overtime is "
                 + teamScore + " to " + enemyScore + ". It's a tie!");
               //If the numbers are the same again, the game will 
               //End in a tie
               ties++;
              }
    }
     //Change state to post game
     state = POST_GAME;
   }
   
   if (state==POST_GAME) {
     //Prompt to play again
     out.println("Would you like to play again? (y/n)");
     out2.println("Would you like to play again? (y/n)");
     String response = in.readLine();
     String response2 = in2.readLine();
     
     //Both players have to want to play again to restart the game
     if(response.equals("y") && (response2.equals("y"))) {
        // If y is entered twice, restart from the beginning
        
        //Clear list and re-add names
        pl.resetList(nameList);
        pl2.resetList(nameList);

        //Clear team and enemy lists
        pl.resetList(teamList);
        pl2.resetList(enemyList);
        
        //Reset the selection of the nameList with the PlayerList object
        nameList = pl.chooseArrayList();
        
        out.println("OK. Good luck");
        out2.println("OK. Good luck");
        
        state = INTRO;
     }
     else if (response.equals("n") || (response2.equals("n"))) {
         /* If at least one n is entered, display final stats
         and exit the game */
         out.println("The final score was: " + teamName + ": " 
            + teamWins + " to Computer: " + enemyWins + " with " 
            + ties + " ties." + " Thanks for playing!");
         out2.println("The final score was: " + teamName + ": " 
            + teamWins + " to Computer: " + enemyWins + " with " 
            + ties + " ties." + " Thanks for playing!");   
         work = false;
         System.exit(0);
     }
     else 
         out.println("Please enter a valid input (y/n)");
   }
 }
}
}