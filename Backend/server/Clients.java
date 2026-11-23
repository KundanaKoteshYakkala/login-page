package server;
import java.net.*;
import java.io.*;
import databaseConnection.Database;

class Clients {
	Socket socket;
	BufferedReader in;
	PrintStream out;
	HTTP http;
	Clients(Socket socket) throws IOException{
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());
		
		http = new HTTP();
		http.readRequest(in);
		client();
	}
	
	public void client() throws IOException{
		String request = http.getRequest();
		System.out.println(request);
		if(request.equals("/")) {
			String indexHTML = readFile("Frontend/index.html");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/html");
			out.println("Content-Length: " + indexHTML.length());
			out.println("Connection: close");
			out.println("");
			out.println(indexHTML);
			
			System.out.println("index.html sent");
		}
		
		else if(request.equals("/style.css")) {
			String styleCSS = readFile("Frontend/style.css");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/css");
			out.println("Content-Length: " + styleCSS.length());
			out.println("Connection: close");
			out.println("");
			out.println(styleCSS);
		}
		else if(request.equals("/register.html?")) {
			String registerHTML = readFile("Frontend/register.html");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/html");
			out.println("Content-Length: " + registerHTML.length());
			out.println("Connection: close");
			out.println("");
			out.println(registerHTML);
		}
		else if(request.equals("/register.css")) {
			String registerCSS = readFile("Frontend/register.css");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/css");
			out.println("Content-Length: " + registerCSS.length());
			out.println("Connection: close");
			out.println("");
			out.println(registerCSS);
		}
		else if(request.equals("/login.html?")) {
			String loginHTML = readFile("Frontend/login.html");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/html");
			out.println("Content-Length: " + loginHTML.length());
			out.println("Connection: close");
			out.println("");
			out.println(loginHTML);
		}
		else if(request.equals("/login.css")) {
			String loginCSS = readFile("Frontend/login.css");
			out.println("HTTP/1.1 200 OK");
			out.println("Content-type: text/css");
			out.println("Content-Length: " + loginCSS.length());
			out.println("Connection: close");
			out.println("");
			out.println(loginCSS);
		}
		else if(request.equals("/userData")) {
			String data = http.getData();
			System.out.println(data);
			Database.sendRegisterData(data);
		}
		else if(request.equals("/verifyLoginCreds")) {
			String data = http.getData();
			System.out.println(data);
			boolean validUser = Database.verifyLoginCreds(data);
			if(validUser) System.out.println("Login success");
			else System.out.println("Login failed");
		}
		else {
			out.println("HTTP/1.1 404 Not Found");
			out.println("Content-Length: 0");
			out.println("Connection: close");
			out.println("");
		}
		
	}
	
	String readFile(String filePath) {
		String content = new String();
		try(BufferedReader fin = new BufferedReader(new FileReader(filePath))){
			String line = new String();
			while((line = fin.readLine()) != null) content += line + "\n";
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
