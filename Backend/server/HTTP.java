package server;
import java.io.*;

public class HTTP {
	String method;
	String request;
	String connection;
	int contentLength = 0;
	String data = new String();
	
	void readRequest(BufferedReader in) {
		try {
			String line = new String();
			int lineNo = 0;
			while(!(line = in.readLine()).equals("")) {
				++lineNo;
				String[] words = line.split(" ");
				if(lineNo == 1) {
					method = words[0];
					request = words[1];
				}
				
				if(lineNo == 3) connection = words[1];
				
				if(lineNo == 4) if(words[0].equals("Content-Length:")) contentLength = Integer.parseInt(words[1]);
			}
			
			if(contentLength != 0) setData(in);
//			System.out.println(method);
//			System.out.println(request);
//			System.out.println(connection);
//			System.out.println(contentLength);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void setData(BufferedReader in) throws IOException{
		for(int i=0; i<contentLength; i++) {
			data += (char)in.read();
		}
	}
	
	String getMethod() {
		return method;
	}
	
	String getRequest() {
		return request;
	}
	
	int getContentLength() {
		return contentLength;
	}
	
	String getData() {
		return data;
	}
}
