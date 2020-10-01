package com.net.java;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static Store store;
	
	public static void main(String[] args) {
		store = new Store();
		boolean registerSignupLoop = true;
		String username, email, password;
		registerLoginMenu();
		while(registerSignupLoop) {
			System.out.print("\nEnter your choice (Press '4' to show menu again) : ");
			int ch = sc.nextInt();
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
						store.registerUser(username, email, password);
					}
					else {
						System.out.println("Passwords do not match. Try registering again!");
					}
					break;
				case 2:
					if(store.getStoreLength() > 0) {
						System.out.print("Enter your username : ");
						username = sc.next();
						System.out.print("Enter your password : ");
						password = sc.next();
						User obj = store.loginUser(username, password);
						if(obj != null) {
							System.out.println("Welcome " + obj.getUsername() + "! What are you up to today?");
							boolean userLoop = true;
							showMenu();
							while(userLoop) {
								System.out.print("\nEnter your choice (Press '5' to show menu again) : ");
								int choice = sc.nextInt();
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
					break;
				case 4:
					registerLoginMenu();
					break;
			}
		}
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
