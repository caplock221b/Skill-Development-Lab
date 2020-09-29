package com.net.java;

import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class TaskList {
	private String title;
	private String datetime;
	private Vector<String> tasks;
	
	public TaskList(String title, Vector<String> tasks) {
		this.title = title;
		this.tasks = tasks;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();
	    this.datetime = formatter.format(date);
	}
	
	public String getTitle() {
		return title;
	}

	public String getDatetime() {
		return datetime;
	}

	public void getTasks() {
		Enumeration<String> e = tasks.elements();
		int cnt = 1;
		while(e.hasMoreElements()) {
			System.out.println("\t\t" + cnt + ". " + e.nextElement());
			cnt++;
		}
	}
	
	public TaskList getTaskList() {
		return this;
	}
}