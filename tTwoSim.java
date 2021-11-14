/**
 * tTwoSim - Synchronization for enemy score
 *
 * @author (Sean Kennedy)
 * @version (Final Project - Enhanced)
 */
public class tTwoSim implements Runnable {
    SimGame t2;
    int score;
    
    //Add the score int value along with SimGame object
    tTwoSim(SimGame t2, int score) {
        this.t2 = t2;
        this.score = score;
    }
    
    //Run with 4 as the arg for quarter length (as discussed in SimGame comments)
    public void run() {
        score = t2.simScore(4);
     }
}