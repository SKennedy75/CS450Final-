import java.net.*;
import java.io.*;
/**
 * Pickup Client Class
 *
 * @author (Sean Kennedy)
 * @version (Assignment 3 - Enhanced Version)
 */
public class PC {
    public static void main(String[] args) throws IOException {
        //Initiate connection request to server's IP address and port number
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        
        //Read in IP address and port number
        String teamName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            //Create socket, PrintWriter and BufferedReaders
            Socket pgSocket = new Socket(teamName, portNumber);
            PrintWriter out = new PrintWriter(pgSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(pgSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            
            //Receive message from server
            while ((fromServer = in.readLine()) != null) {
                //Print message from server
                System.out.println("Server: " + fromServer);
                
                if (fromServer.equals("Welcome to Pickup Basketball. Please enter " 
                + "a team name:") || fromServer.equals("It is your turn to select")
                || fromServer.equals("Would you like to play again? (y/n)")
                || (fromServer.equals("You can type to your opponent before the game begins " +
                "(type 'adv' to advance)"))) {
                    
                    //Read response from client only if a message prompts it
                    fromUser = stdIn.readLine();
                    
                    if (fromUser != null) {
                        //Send response to server
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                    }
                    
                    //If the client enters quit, the connection will stop
                    if (fromUser.equals("quit"))
                        break;
                }
            }
            //Error handling
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host" + teamName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " 
            +teamName);
            System.exit(1);
        }
    }
}