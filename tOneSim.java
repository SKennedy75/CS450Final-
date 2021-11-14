/**
 * tOneSim - Synchronization for team score
 *
 * @author (Sean Kennedy)
 * @version (Final Project - Enhanced)
 */
public class tOneSim implements Runnable {
    SimGame t1;
    int score;
    
    //Add the score int value along with SimGame object
    tOneSim(SimGame t1, int score) {
        this.t1 = t1;
        this.score = score;
    }
    
    //Run with 4 as the arg for quarter length (as discussed in SimGame comments)
    public void run() {
        score = t1.simScore(4);
    }
}
