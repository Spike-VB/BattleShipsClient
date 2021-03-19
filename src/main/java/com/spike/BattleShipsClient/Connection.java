package com.spike.BattleShipsClient;

import java.net.*;
import java.io.*;
import com.spike.BattleShipsLib.*;

public class Connection {
	
	private Socket sock;
	private ObjectOutputStream oos;
	
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
			oos.writeObject(ships);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendFire(int[] position) {
		try {
			oos.writeObject(position);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
