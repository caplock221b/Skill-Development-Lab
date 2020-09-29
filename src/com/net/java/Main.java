package com.net.java;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean loop = true;
		System.out.print("Enter your username : ");
		String name = sc.nextLine();
		
		System.out.print("Enter your email : ");
		String email = sc.nextLine();
		
		User obj = new User(name, email);
		showMenu();
		while(loop) {
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
					loop = false;
					break;
				case 5:
					showMenu();
					break;
			}
		}
	}
	
	private static void showMenu() {
		System.out.println("\nMenu : ");
		System.out.println("1. Add a new task list");
		System.out.println("2. Remove a task list");
		System.out.println("3. Show all task lists");
		System.out.println("4. Exit");
	}
	
}
