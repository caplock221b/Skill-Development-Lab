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
	
	private int checkUser(User user, int caller) {
		Iterator<User> i = store.iterator();
		int ctr = 0;
		if(caller == 0) {
			while(i.hasNext()) {
				User ob = i.next();
				if(ob.getUsername().equals(user.getUsername()) && ob.getEmail().equals(user.getEmail()) && ob.getPassword().equals(user.getPassword())) {
					return ctr;
				}
				ctr++;
			}
		}
		else {
			while(i.hasNext()) {
				User ob = i.next();
				if(ob.getUsername().equals(user.getUsername()) && ob.getPassword().equals(user.getPassword())) {
					return ctr;
				}
				ctr++;
			}
		}
		return -1;
	}
	
	public boolean registerUser(User user) {
		if(checkUser(user, 0) != -1) {
			return false;
		}
		else {
			store.add(user);
			return true;
		}
	}
	
	public User loginUser(User user) {
		int result = checkUser(user, 1);
		if(result == -1) {
			System.out.println("User not registered. Try registering the user first.");
			return null;
		}
		return store.get(result);
	}
}
