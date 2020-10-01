package com.net.java;

import java.util.Scanner;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class User {
	private String username;
	private String email;
	private List<TaskList> arr;
	private HashSet<String> hs;
	
	private static Scanner sc = new Scanner(System.in);
	
	public User(String name, String email) {
		this.username = name;
		this.email = email;
		this.arr = new ArrayList<TaskList>();
		this.hs = new HashSet<String>();
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void getArr() {
		if(hs.isEmpty()) {
			System.out.println("Looks like you don't have any task lists yet!");
			System.out.println("You've caught up to everything!");
		}
		else {
			Iterator<TaskList> i = arr.iterator();
			int ctr = 1;
			while(i.hasNext()) {
				TaskList tl = (TaskList) i.next();
				System.out.println("\tTask List #" + ctr + " :");
				System.out.println("\tTitle : " + tl.getTitle());
				System.out.println("\tCreated at : " + tl.getDatetime());
				System.out.println("\tTasks : ");
				tl.getTasks();
				System.out.println("\n");
				ctr++;
			}
		}
	}
	
	public void addNewTaskList() {
		System.out.println("Enter title of new task list : ");
		String title = sc.nextLine();
		while(hs.contains(title.toLowerCase())) {
			System.out.println("A task list with the same title already exists!");
			System.out.print("Please enter a different title for the task list : ");
			title = sc.nextLine();
		}
		hs.add(title.toLowerCase());
		Vector<String> v = new Vector<String>();
		int ctr = 1;
		while(true) {
			System.out.print("Enter task #" + ctr + " : ");
			v.addElement(sc.nextLine());
			System.out.println("Add another task? (Enter 'y' for yes and 'n' for no) : ");
			String choice = sc.nextLine();
			if(choice.equals("n") || choice.equals("N")) {
				break;
			}
			ctr++;
		}
		TaskList t = new TaskList(title, v);
		arr.add(t);
	}
	
	public void removeTaskList() {
		System.out.print("Enter the title of task list to be removed : ");
		String name = sc.nextLine();
		if(hs.contains(name.toLowerCase())) {
			Iterator<TaskList> i = arr.iterator();
			int ctr = 0;
			while(i.hasNext()) {
				TaskList tl = i.next();
				if(tl.getTitle().toLowerCase().equals(name.toLowerCase())) {
					arr.remove(ctr);
					break;
				}
				ctr++;
			}
			System.out.println(name + " task list was removed.");
			hs.remove(name.toLowerCase());
		}
		else {
			System.out.println("Task list with the name " + name + " does not exist.");
		}
	}
}
