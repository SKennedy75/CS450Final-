import java.util.Random;
import java.util.ArrayList;
/**
 * PlayerList class - contains methods helpful for the draft phase
 *
 * @author (Sean Kennedy)
 * @version (Final Project Enhanced Version)
 */
public class PlayerList
{
    Random rnd = new Random();
                    
    //Choose the list of names
    public ArrayList<String> chooseArrayList() {
        //Initial list 1 of players available to draft
        ArrayList<String> nameList = new ArrayList<String>() {
            {
                add("Jim Leonard");
                add("Rashard Smith"); 
                add("Drew Williams");
                add("Pablo Randov"); 
                add("Steven Jones"); 
                add("John Kennedy"); 
                add("Tim Lance"); 
                add("Trey Barrimore");
                add("Julius Barret"); 
                add("Ivan Rabb");
            }
        };
    
        //Initial list 2 of players available to draft
        ArrayList<String> nameList2 = new ArrayList<String>() {
            {
                add("Adrian Barrett");
                add("Joseph Pena"); 
                add("Arthur Hanson");
                add("Pablo Randov"); 
                add("Malcolm Mason"); 
                add("Hector Mcbride"); 
                add("Wilbert Wolfe"); 
                add("Antonio Warren");
                add("Javier Horton"); 
                add("Merle Parker");
            }
        };
    
        //Initial list 3 of players available to draft
        ArrayList<String> nameList3 = new ArrayList<String>() {
            {
                add("Corey Higgins");
                add("Stephen Gregory"); 
                add("Bernard Bishop");
                add("Zachary Leonard"); 
                add("Micheal Mccarthy"); 
                add("Buck Fredrickson"); 
                add("Tim Lance"); 
                add("Jackson Poore");
                add("Vance Hawk"); 
                add("Quinton Banner");
            }
        };
        
        //Randomly decide which list to let players choose from
        int listNum = rnd.nextInt(4);
        if (listNum == 1) {
            return nameList;
        }
        if (listNum == 2) {
            return nameList2;
        }
        else
            return nameList3;
    }
    
    
    //Reset the names after the game has been played
    public void resetList(ArrayList<String> nl) {
        nl.clear();
    }
    
    //Draft method to select players from players list
    public void draft(ArrayList<String> nl, ArrayList<String> tl,
    int index) {
        //Use index to get the name of the player from ArrayList
        String pName = nl.get(index);
        //Add that name to the team/enemy list depending on player one or two
        tl.add(pName);
        //Remove that name from available players list
        nl.remove(index);
    }
    
    //Returns the name of the player selected
    public String selected(ArrayList<String> nl, int index) {
        String pName = nl.get(index);
        return pName;
    }
    
    //Updates the list at the start of each change in draft order
    public ArrayList<String> update(ArrayList<String> nu) {
        return nu;
    }
}
