package com.net.java;

import java.util.Scanner;
//import java.util.List;
//import java.util.Vector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String password;
//	private List<TaskList> arr;
//	private HashSet<String> hs;
	
	private static Scanner sc = new Scanner(System.in);
	
	public User(String name, String email, String password) {
		this.username = name;
		this.email = email;
		this.password = password;
//		this.arr = new ArrayList<TaskList>();
//		this.hs = new HashSet<String>();
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public User getUser() {
		return this;
	}
	
	public void getArr(Connection connection, int id) {
		int ctr = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists WHERE user_id=" + id);
			while(rs.next()) {
				ctr++;
				System.out.println("\tTask List #" + ctr + " :");
				System.out.println("\tTitle : " + rs.getString("title"));
				System.out.println("\tCreated on " + rs.getString("date") + " at " + rs.getString("time"));
				System.out.println("\tTasks : ");
				int i = rs.getInt("id");
				Statement statement2 = connection.createStatement();
				ResultSet rs2 = statement2.executeQuery("SELECT * FROM tasks WHERE tasklist_id=" + i);
				int countOfTasks = 0;
				while(rs2.next()) {
					countOfTasks++;
					System.out.println("\t\t" + countOfTasks + ". " + rs2.getString("task"));
				}
				System.out.println("\n");
			}
			if(ctr == 0) {
				System.out.println("Looks like you don't have any task lists yet!");
				System.out.println("You've caught up to everything!");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

//	public void getArr() {
//		if(hs.isEmpty()) {
//			System.out.println("Looks like you don't have any task lists yet!");
//			System.out.println("You've caught up to everything!");
//		}
//		else {
//			Iterator<TaskList> i = arr.iterator();
//			int ctr = 1;
//			while(i.hasNext()) {
//				TaskList tl = (TaskList) i.next();
//				System.out.println("\tTask List #" + ctr + " :");
//				System.out.println("\tTitle : " + tl.getTitle());
//				System.out.println("\tCreated at : " + tl.getDatetime());
//				System.out.println("\tTasks : ");
//				tl.getTasks();
//				System.out.println("");
//				ctr++;
//			}
//		}
//	}
	
	public void addNewTaskList(Connection connection, int id) {
		System.out.println("Enter title of new task list : ");
		String title = sc.nextLine();
		try {
			PreparedStatement prep = connection.prepareStatement("INSERT INTO tasklists(user_id, title, date, time) VALUES ("+ id +", '"+ title +"', curdate(), curtime());");
			prep.executeUpdate();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists");
			int tasklist_id = 0;
			while(rs.next()) {
				tasklist_id = rs.getInt("id");
			}
			int ctr = 1;
			while(true) {
				System.out.print("Enter task #" + ctr + " : ");
				String task = sc.nextLine();
				prep = connection.prepareStatement("INSERT INTO tasks(tasklist_id, task) VALUES ("+ tasklist_id +", '"+ task +"');");
				prep.executeUpdate();
				System.out.println("Add another task? (Enter 'y' for yes and 'n' for no) : ");
				String choice = sc.nextLine();
				if(choice.equals("n") || choice.equals("N")) {
					break;
				}
				ctr++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
//	public void addNewTaskList() {
//		System.out.println("Enter title of new task list : ");
//		String title = sc.nextLine();
//		while(hs.contains(title.toLowerCase())) {
//			System.out.println("A task list with the same title already exists!");
//			System.out.print("Please enter a different title for the task list : ");
//			title = sc.nextLine();
//		}
//		hs.add(title.toLowerCase());
//		Vector<String> v = new Vector<String>();
//		int ctr = 1;
//		while(true) {
//			System.out.print("Enter task #" + ctr + " : ");
//			v.addElement(sc.nextLine());
//			System.out.println("Add another task? (Enter 'y' for yes and 'n' for no) : ");
//			String choice = sc.nextLine();
//			if(choice.equals("n") || choice.equals("N")) {
//				break;
//			}
//			ctr++;
//		}
//		TaskList t = new TaskList(title, v);
//		arr.add(t);
//	}
	
	public void removeTaskList(Connection connection, int id) {
		System.out.print("Enter the title of task list to be removed : ");
		String name = sc.nextLine();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists");
			boolean flag = false;
			while(rs.next()) {
				if(name.toLowerCase().equals(rs.getString("title").toLowerCase())) {
					Statement st = connection.createStatement();
					int i = rs.getInt("id");
					st.executeUpdate("DELETE FROM tasklists WHERE id=" + i);
					System.out.println(name + " task list was removed.");
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("Task list with the name " + name + " does not exist.");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
//	public void removeTaskList() {
//		System.out.print("Enter the title of task list to be removed : ");
//		String name = sc.nextLine();
//		if(hs.contains(name.toLowerCase())) {
//			Iterator<TaskList> i = arr.iterator();
//			int ctr = 0;
//			while(i.hasNext()) {
//				TaskList tl = i.next();
//				if(tl.getTitle().toLowerCase().equals(name.toLowerCase())) {
//					arr.remove(ctr);
//					break;
//				}
//				ctr++;
//			}
//			System.out.println(name + " task list was removed.");
//			hs.remove(name.toLowerCase());
//		}
//		else {
//			System.out.println("Task list with the name " + name + " does not exist.");
//		}
//	}
}
