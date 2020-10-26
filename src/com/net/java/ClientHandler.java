package com.net.java;

import util.DbData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;

public class ClientHandler extends Thread {
	
	private Socket client;
	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;
	
	public ClientHandler(Socket clientSocket) throws IOException{
		this.client = clientSocket;
		toClient = new ObjectOutputStream(client.getOutputStream());
		fromClient = new ObjectInputStream(client.getInputStream());
	}
	
	@Override
	public void run() {
		try {
			Store store = new Store();
			
			String url = DbData.getUrl();
			String username = DbData.getUsername();
			String password = DbData.getPassword();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to database...");
			
			boolean registerSignupLoop = true;
			
			while(registerSignupLoop) {
				int ch = (int) fromClient.readObject();
				switch(ch) {
					case 1:
						User user = (User) fromClient.readObject();
						boolean checkReg = store.registerUser(connection, user);
						if(checkReg) {
							System.out.println("\tNew user successfully registered.");
							toClient.writeObject("New user successfully registered. Log in to start the journey with us!");
						}
						else {
							System.out.println("\t Duplicate user found.");
							toClient.writeObject("User with the given credentials is already registered. Try logging in.");
						}
						break;
					case 2:
						int storeLength = store.getStoreLength(connection);
						toClient.writeObject(storeLength);
						if(storeLength > 0) {
							User u = (User) fromClient.readObject();
							User obj = store.loginUser(connection, u);
							toClient.writeObject(obj);
							if(obj != null) {
								String name = obj.getUsername();
								System.out.println("\n\tUser " + name + " logged in.");
								boolean userLoop = true;
								int id = store.checkUser(connection, obj, 1);
								toClient.writeObject(id);
								while(userLoop) {
									int choice = (int) fromClient.readObject();
									switch(choice) {
										case 1:
											System.out.println("\tUser " + name + " added a new task list.");
											break;
										case 2:
											System.out.println("\tUser " + name + " attempting to remove a task list.");
											break;
										case 3:
											System.out.println("\tUser " + name + " viewing their task lists. ");
											break;
										case 4:
											userLoop = false;
											System.out.println("\tUser " + name + " logged out.");
											break;
										case 5:
											break;
									}
								}
								System.out.println();
							}
						}
						break;
					case 3:
						registerSignupLoop = false;
						break;
					case 4:
						break;
				}
			}
			client.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
