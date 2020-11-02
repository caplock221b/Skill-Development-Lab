package com.net.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Client {
	private static ObjectOutputStream toServer;
	private static ObjectInputStream fromServer;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/sdl";
		String username = "root";
		String password = "atharva16!";
		Class.forName("com.mysql.cj.jdbc.Driver");

		Socket client = new Socket("localhost", 9999);
		
		new MenuFrame();

		try {
			DriverManager.getConnection(url, username, password);
			toServer = new ObjectOutputStream(client.getOutputStream());
			fromServer = new ObjectInputStream(client.getInputStream());

			boolean registerSignupLoop = true;
			while (registerSignupLoop) {
				int ch = (int) fromServer.readObject();
				switch (ch) {
					case 1:
						boolean f = (boolean) fromServer.readObject();
						if(f) {
							RegisterFrame.showDialogBox("Registration successful!", "Please login to start.", 1);
						}
						else {
							RegisterFrame.showDialogBox("User already exists!", "Please login to start.", 1);
						}
						break;
					case 2:
						User obj = (User) fromServer.readObject();
						if (obj != null) {
							fromServer.readObject();
							new MainPageFrame();
							boolean userLoop = true;
							while (userLoop) {
								int choice = (int) fromServer.readObject();
								switch (choice) {
									case 1:
									case 2:
									case 3:
										break;
									case 4:
										userLoop = false;
										break;
								}
							}
						}
						else {
							LoginFrame.showDialogBox("User does not exist!", "Please register to start.", 1);
						}
						break;
					case 3:
						registerSignupLoop = false;
						System.out.println("Goodbye! Have a nice day!");
						break;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		client.close();
	}
	
	public static void sendChoice(int ch) throws IOException {
		toServer.writeObject(ch);
	}
	
	public static void sendUser(User user) throws IOException {
		toServer.writeObject(user);
	}
	
	public static void sendString(String str) throws IOException {
		toServer.writeObject(str);
	}
}
