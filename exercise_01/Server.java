package chat;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
				
		try {
			serverSocket = new ServerSocket( 4711 );
		} catch (IOException e) {
			System.err.println(" Error with I/O!");
		}
		
		while(true) {
			Socket client = serverSocket.accept();
		}
	} 
}
