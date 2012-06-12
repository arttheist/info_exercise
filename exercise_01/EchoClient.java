package chat;

import java.io.*;
import java.net.*;

// 
public class EchoClient {

    //  public final static int ECHO_PORT = 7;
    public final static int ECHO_PORT = 4711;
    
    public static void main(String[] argv) throws IOException {
    if (argv.length != 1) {
        System.err.println("usage: RemoteEcho <host-name>");
        System.exit(1); // Programmende mit Fehlercode 1
    }      
    Socket socket = null;
    PrintWriter sockPrintWriter = null;
    BufferedReader sockBuffReader = null;
    // oeffne socket schreiben:
    try {
        socket = new Socket(argv[0], ECHO_PORT);
        sockPrintWriter =
        new PrintWriter(socket.getOutputStream(), true);
    } catch (UnknownHostException e) {
        System.err.println("Unknown host: "+argv[0]);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Connection to "+argv[0]+" failed.");
        System.exit(1);
    }
    System.out.println("Ready to transmit, please enter some text!");
    // von Kommandozeile lesen
    BufferedReader stdIn =
        new BufferedReader(new InputStreamReader(System.in));
    String string;
    while ((string = stdIn.readLine()) != null) {
        // zum server schicken
        sockPrintWriter.println(string);
        // antwort ausgeben
    }
    // aufraeumen
    sockPrintWriter.close();
    sockBuffReader.close();
    stdIn.close();
    socket.close();
    }
}
