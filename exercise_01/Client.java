package chat;

import java.net.*;
import java.io.*;


public class Client {
	public static void main(String[] args) throws IOException {
	
		Socket clientSocket = null;
		PrintWriter out = null;
        BufferedReader in = null;
	
		try {
			clientSocket = new Socket("localhost", 9999);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
            		clientSocket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Error with I/O while Connection with localhost!");
			System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
		String userInput;
		
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		
		out.close();
		in.close();
		stdIn.close();
		clientSocket.close();
	}
	
}
