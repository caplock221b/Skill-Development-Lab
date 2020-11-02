package com.net.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Store {
	public int getStoreLength(Connection connection) {
		int ctr = -1;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users");
			ctr = 0;
			while (rs.next()) {
				ctr++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return ctr;
	}

	public int checkUser(Connection connection, User user, int caller) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users");
			if (caller == 0) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String email = rs.getString("email");
					if (username.equals(user.getUsername()) && password.equals(user.getPassword())
							&& email.equals(user.getEmail())) {
						return id;
					}
				}
			} else {
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
						return id;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return -1;
	}

	public boolean registerUser(Connection connection, User user) {
		if (checkUser(connection, user, 0) != -1) {
			return false;
		} else {
			try {
				PreparedStatement prep = connection
						.prepareStatement("INSERT INTO users(username, password, email) VALUES ('" + user.getUsername()
								+ "','" + user.getPassword() + "','" + user.getEmail() + "');");
				prep.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
			return true;
		}
	}

	public User loginUser(Connection connection, User user) {
		int result = checkUser(connection, user, 1);
		if (result == -1) {
			System.out.println("User not registered. Try registering the user first.");
			return null;
		}
		User user1;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id=" + result);
			rs.next();
			user1 = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"));
			return user1;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
}
