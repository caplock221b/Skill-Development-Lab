package com.net.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Store {
	private static List<User> store;
	
	public Store() {
		store = new ArrayList<User>();
	}
	
	public int getStoreLength() {
		return store.size();
	}
	
	private int checkUser(String username, String email, String password, int caller) {
		Iterator<User> i = store.iterator();
		int ctr = 0;
		if(caller == 0) {
			while(i.hasNext()) {
				User ob = i.next();
				if(ob.getUsername().equals(username) && ob.getEmail().equals(email) && ob.getPassword().equals(password)) {
					return ctr;
				}
				ctr++;
			}
		}
		else {
			while(i.hasNext()) {
				User ob = i.next();
				if(ob.getUsername().equals(username) && ob.getPassword().equals(password)) {
					return ctr;
				}
				ctr++;
			}
		}
		return -1;
	}
	
	public void registerUser(String username, String email, String password) {
		if(checkUser(username, email, password, 0) != -1) {
			System.out.println("User with the given credentials is already registered. Try logging in.");
		}
		else {
			User ob = new User(username, email, password);
			store.add(ob);
			System.out.println("New user successfully registered. Log in to start the journey with us!");
		}
	}
	
	public User loginUser(String username, String password) {
		int result = checkUser(username, "", password, 1);
		if(result == -1) {
			System.out.println("User not registered. Try registering the user first.");
			return null;
		}
		return store.get(result);
	}
}
