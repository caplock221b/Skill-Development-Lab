package com.net.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args)throws IOException, ClassNotFoundException {
		Store store = new Store();
		
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("[SERVER] started at port 9999...");
		System.out.println("[SERVER] awaiting client connnection...");
		
		Socket server = serverSocket.accept();
		System.out.println("[SERVER] client connected...");
		
		ObjectOutputStream toClient = new ObjectOutputStream(server.getOutputStream());
		ObjectInputStream fromClient = new ObjectInputStream(server.getInputStream());
		
		boolean registerSignupLoop = true;
		
		while(registerSignupLoop) {
			int ch = (int) fromClient.readObject();
			System.out.println(ch);
			switch(ch) {
				case 1:
					User user = (User) fromClient.readObject();
					store.registerUser(user);
				case 2:
					int storeLength = store.getStoreLength();
					toClient.write(storeLength);
					if(storeLength > 0) {
						User u = (User) fromClient.readObject();
						User obj = store.loginUser(u);
						toClient.writeObject(obj);
						if(obj != null) {
							boolean userLoop = true;
							while(userLoop) {
								int choice = fromClient.read();
								switch(choice) {
									case 1:
									case 2:
									case 3:
										break;
									case 4:
										userLoop = false;
										break;
									case 5:
										break;
								}
							}
						}
					}
					else {
						
					}
					break;
				case 3:
					registerSignupLoop = false;
					break;
				case 4:
					break;
			}
		}
		System.out.println("[SERVER] closing server...");
		server.close();
		serverSocket.close();
	}

}
