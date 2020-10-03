package com.net.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)throws IOException, ClassNotFoundException {
		Socket client = new Socket("localhost", 9999);
		
		ObjectOutputStream toServer = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream fromServer = new ObjectInputStream(client.getInputStream());
		
		boolean registerSignupLoop = true;
		String username, email, password;
		System.out.println("------------Welcome to List-It App!------------");
		registerLoginMenu();
		while(registerSignupLoop) {
			System.out.print("\nEnter your choice (Press '4' to show menu again) : ");
			int ch = sc.nextInt();
			toServer.writeObject(ch);
			switch(ch) {
				case 1:
					System.out.print("Enter your username : ");
					username = sc.next();
					System.out.print("Enter your email address : ");
					email = sc.next();
					System.out.print("Enter your password : ");
					password = sc.next();
					System.out.print("Please confirm your password : ");
					String p = sc.next();
					if(password.equals(p)) {
						User user = new User(username, email, password);
						toServer.writeObject(user);
						System.out.println((String) fromServer.readObject());
					}
					else {
						System.out.println("Passwords do not match. Try registering again!");
					}
					break;
				case 2:
					int storeLength = (int) fromServer.readObject();
					if(storeLength > 0) {
						System.out.print("Enter your username : ");
						username = sc.next();
						System.out.print("Enter your password : ");
						password = sc.next();
						User user = new User(username, "", password);
						toServer.writeObject(user);
						User obj = (User) fromServer.readObject();
						if(obj != null) {
							System.out.println("Welcome " + obj.getUsername() + "! What are you up to today?");
							boolean userLoop = true;
							showMenu();
							while(userLoop) {
								System.out.print("\nEnter your choice (Press '5' to show menu again) : ");
								int choice = sc.nextInt();
								toServer.writeObject(choice);
								switch(choice) {
									case 1:
										obj.addNewTaskList();
										break;
									case 2:
										obj.removeTaskList();
										break;
									case 3:
										obj.getArr();
										break;
									case 4:
										System.out.println("We'll be waiting for you to come back! Bye!");
										userLoop = false;
										registerLoginMenu();
										break;
									case 5:
										showMenu();
										break;
								}
							}
						}
					}
					else {
						System.out.println("There are no registered users. Try registering first!");
					}
					break;
				case 3:
					registerSignupLoop = false;
					System.out.println("Goodbye! Have a nice day!");
					break;
				case 4:
					registerLoginMenu();
					break;
			}
		}
		client.close();
	}
	
	private static void registerLoginMenu() {
		System.out.println("\nRegister / Login Menu : ");
		System.out.println("1. Register new user");
		System.out.println("2. Login");
		System.out.println("3. Exit App");
	}
	
	private static void showMenu() {
		System.out.println("\nMenu : ");
		System.out.println("1. Add a new task list");
		System.out.println("2. Remove a task list");
		System.out.println("3. Show all task lists");
		System.out.println("4. Logout");
	}
}
