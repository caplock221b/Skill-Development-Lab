package com.net.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;

public class ClientHandler extends Thread {

	private Socket client;
	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;

	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		toClient = new ObjectOutputStream(client.getOutputStream());
		fromClient = new ObjectInputStream(client.getInputStream());
	}

	@Override
	public void run() {
		try {
			Store store = new Store();

			String url = "jdbc:mysql://localhost:3306/sdl";
			String username = "root";
			String password = "atharva16!";

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to database...");

			boolean registerSignupLoop = true;

			while (registerSignupLoop) {
				int ch = (int) fromClient.readObject();
				toClient.writeObject(ch);
				switch (ch) {
					case 1:
						User user = (User) fromClient.readObject();
						boolean checkReg = store.registerUser(connection, user);
						if (checkReg) {
							System.out.println("\tNew user successfully registered.");
							// New user successfully registered
							toClient.writeObject(true);
						} else {
							System.out.println("\t Duplicate user found.");
							// user already exists
							toClient.writeObject(false);
						}
						break;
					case 2:
						User u = (User) fromClient.readObject();
						User obj = store.loginUser(connection, u);
						toClient.writeObject(obj);
						if (obj != null) {
							String name = obj.getUsername();
							System.out.println("\n\tUser " + name + " logged in.");
							boolean userLoop = true;
							int id = store.checkUser(connection, obj, 1);
							toClient.writeObject(id);
							while (userLoop) {
								int choice = (int) fromClient.readObject();
								toClient.writeObject(choice);
								switch (choice) {
									case 1:
										System.out.println("\tUser " + name + " added a new task list.");
										String title = (String) fromClient.readObject();
										int tasklist_id = obj.addNewTasklist(connection, id, title);
										while(true) {
											String task = (String) fromClient.readObject();
											obj.addNewTask(connection, tasklist_id, task);
											int again = (int) fromClient.readObject();
											if(again==0) {
												break;
											}
										}
										break;
									case 2:
										System.out.println("\tUser " + name + " attempting to remove a task list.");
										title = (String) fromClient.readObject();
										obj.removeTaskList(connection, id, title);
										break;
									case 3:
										System.out.println("\tUser " + name + " viewing their task lists. ");
										obj.showTasks(connection, id);
										break;
									case 4:
										userLoop = false;
										System.out.println("\tUser " + name + " logged out.");
										break;
								}
							}
							System.out.println();
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
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
