package com.net.java;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String password;

	public User(String name, String email, String password) {
		this.username = name;
		this.email = email;
		this.password = password;
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
	
	public void showTasks(Connection connection, int id) {
		int ctr = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists WHERE user_id=" + id);
			while (rs.next()) {
				ctr++;
				String title = rs.getString("title");
				String tasks = "";
				int i = rs.getInt("id");
				Statement statement2 = connection.createStatement();
				ResultSet rs2 = statement2.executeQuery("SELECT * FROM tasks WHERE tasklist_id=" + i);
				int countOfTasks = 0;
				while (rs2.next()) {
					countOfTasks++;
					tasks += countOfTasks + ". " + rs2.getString("task") + "\n";
				}
				new ShowTasksFrame(ctr, title, tasks);
			}
			if (ctr == 0) {
				ShowTasksFrame.showDialogBox("You don't have any tasks!", "");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public int addNewTasklist(Connection connection, int id, String title) {
		try {
			PreparedStatement prep = connection
					.prepareStatement("INSERT INTO tasklists(user_id, title, date, time) VALUES (" + id + ", '" + title
							+ "', curdate(), curtime());");
			prep.executeUpdate();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists");
			int tasklist_id = 0;
			while (rs.next()) {
				tasklist_id = rs.getInt("id");
			}
			prep.close();
			return tasklist_id;
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}

	public void addNewTask(Connection connection, int tasklist_id, String task) {
		try {
			PreparedStatement prep = connection.prepareStatement(
					"INSERT INTO tasks(tasklist_id, task) VALUES (" + tasklist_id + ", '" + task + "');");
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void removeTaskList(Connection connection, int id, String name) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM tasklists WHERE user_id=" + id);
			boolean flag = false;
			while (rs.next()) {
				if (name.toLowerCase().equals(rs.getString("title").toLowerCase())) {
					Statement st = connection.createStatement();
					int i = rs.getInt("id");
					st.executeUpdate("DELETE FROM tasklists WHERE id=" + i);
					System.out.println(name + " task list was removed.");
					flag = true;
					break;
				}
			}
			if (!flag) {
				RemoveTasklistFrame.showDialogBox("No such tasklist exists!", "", 1);
			}
			else {
				RemoveTasklistFrame.showDialogBox("Tasklist " + name + " was removed.", "", 1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			RemoveTasklistFrame.showDialogBox("Error in database!", "", 1);
		}
	}
}
