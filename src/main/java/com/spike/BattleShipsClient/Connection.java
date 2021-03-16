package com.spike.BattleShipsClient;

import java.net.*;
import java.io.*;

public class Connection {
	
	Socket sock;
	
	public void connect() {
		try {
			sock = new Socket("127.0.0.1", 5050);
			System.out.println("Connection to server is done");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendShips(int[][] ships) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(ships);
			oos.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
