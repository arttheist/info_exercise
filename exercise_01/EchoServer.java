package chat;

import java.net.*;
import java.io.*;

public class EchoServer {

  //public static final int ECHOPORT = 7;
  public static final int ECHOPORT = 4711;

  public static void main(String[] av) {
      ServerSocket sock;
      Socket clientSocket;    
      try {
      sock = new ServerSocket(ECHOPORT);      
      System.out.println("EchoServer ready for connections.");      
      /* Wait for a connection */
      clientSocket = sock.accept();

      System.out.println("Socket starting: " + clientSocket);
      try {
          BufferedReader is =
          new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          String line;
          while ((line = is.readLine()) != null) {
          System.out.println(line);
          }
          clientSocket.close();
      } catch (IOException e) {
          System.out.println("IO Error on socket " + e);
          return;
      }
      System.out.println("Socket ENDED: " + clientSocket);

      } 
      catch(IOException e) {
      /* Crash the server if IO fails. Something bad has happened */
      System.err.println("Could not accept " + e);
      System.exit(1);
      }
  }
}


