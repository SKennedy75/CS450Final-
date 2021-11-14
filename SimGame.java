import java.util.Random;
/**
 * SimGame class will help to simulate the result of the pickup game
 *
 * @author (Sean Kennedy)
 * @version (Version 3 - Enhanced Version)
 */
public class SimGame
{    
    int score;
    //Synchronized method to sim score so both teams can sim at once
    synchronized int simScore(int quarterlength) {
        //4 minute quarters have a realistic score range of 40 - 60.
        
        //I am calculating 4 scores and outputting them to println to
        //show the synchronization in effect
        int q1 = (int)(Math.random()*(15-10)+10);
        System.out.println("q1: " + q1);
        int q2 = (int)(Math.random()*(15-10)+10);
        System.out.println("q2: " + q2);
        int q3 = (int)(Math.random()*(15-10)+10);
        System.out.println("q3: " + q3);
        int q4 = (int)(Math.random()*(15-10)+10);
        System.out.println("q4: " + q4);
        //Add up all quareters for total score
        score = q1 + q2 + q3 + q4;
        System.out.println("total: " + score);
        
        try{  
             Thread.sleep(400);  
        }catch(Exception e){System.out.println(e);}    
        return score;
    }
}
