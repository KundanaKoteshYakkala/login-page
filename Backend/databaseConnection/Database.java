package databaseConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Database {
	static Connection con;
	public static void connect(){
		try {
			String url = "jdbc:mysql://13.206.218.12:3306/users_db";
			String user = "kundan";
			String password = "kundan1234";
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connection successful");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void readData() {
		try {
			String query = "SELECT * FROM users_data";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.println(rs.getString(5));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendRegisterData(String data) {
		Map<String, String> map = new HashMap<>();
		String[] words = data.split("&");
		for(String word: words) {
			String[] keyValue = word.split("=");
			map.put(keyValue[0], keyValue[1]);
		}
		
		for(String key: map.keySet()) System.out.println(key + ": " + map.get(key));
		
		String query = "INSERT INTO users_data VALUES(?, ?, ?, ?, ?)";
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, map.get("name"));
			ps.setString(2, map.get("phone"));
			ps.setString(3, map.get("gmail"));
			ps.setString(4, map.get("userID"));
			ps.setString(5, map.get("password"));
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean verifyLoginCreds(String data) {
		Map<String, String> map = new HashMap<>();
		String[] words = data.split("&");
		for(String word: words) {
			String[] keyValue = word.split("=");
			map.put(keyValue[0], keyValue[1]);
		}
		
		for(String key: map.keySet()) System.out.println(key + ": " + map.get(key));
		
		try {
			String query = "SELECT name, phone, gmail FROM users_data WHERE userID=? AND password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, map.get("userID"));
			ps.setString(2, map.get("password"));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Phone: " + rs.getString("phone"));
				System.out.println("Gmail: " + rs.getString("gmail"));
				return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
