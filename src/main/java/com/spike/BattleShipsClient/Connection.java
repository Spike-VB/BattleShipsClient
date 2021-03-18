package com.spike.BattleShipsClient;

import java.net.*;
import java.io.*;

public class Connection {
	
	Socket sock;
	ObjectOutputStream oos;
	
	public void connect() {
		try {
			sock = new Socket("127.0.0.1", 5050);
			System.out.println("Connection to server is done");
			oos = new ObjectOutputStream(sock.getOutputStream());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendShips(Ship[] ships) {
		try {
			//ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(ships);
			//oos.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendFire(int[] position) {
		try {
			//ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(position);
			//oos.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
