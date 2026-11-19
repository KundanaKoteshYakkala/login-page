package server;
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(5555);
		while(true) {
			Socket socket = server.accept();
			new Clients(socket);
		}
	}
}