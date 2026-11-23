package server;
import java.net.*;
import databaseConnection.Database;
import java.io.*;

public class Server {
	//Server starts here
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(5555);
		Database.connect();
		while(true) {
			Socket socket = server.accept();
			new Clients(socket);
		}
	}
}