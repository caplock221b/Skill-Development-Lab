package com.net.java;

import java.util.Scanner;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;

public class User {
	private String username;
	private String email;
	private List<TaskList> arr;
	
	private static Scanner sc = new Scanner(System.in);
	
	public User(String name, String email) {
		this.username = name;
		this.email = email;
		arr = new ArrayList<TaskList>();
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void getArr() {
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
	
	public void addNewTaskList() {
		System.out.println("Enter title of new task list : ");
		String title = sc.nextLine();
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
		Iterator<TaskList> i = arr.iterator();
		int ctr = 0;
		boolean flag = false;
		while(i.hasNext()) {
			TaskList tl = i.next();
			if(tl.getTitle().toLowerCase().equals(name.toLowerCase())) {
				arr.remove(ctr);
				flag = true;
				break;
			}
			ctr++;
		}
		if(flag) {
			System.out.println(name + " task list was removed.");
		}
		else {
			System.out.println("Task list with the name " + name + " does not exist.");
		}
	}
	
	public User getUser() {
		return this;
	}
}
