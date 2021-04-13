package com.spike.BattleShipsClient;

import java.net.*;
import java.io.*;
import com.spike.BattleShipsLib.*;

public class Connection {
	
	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois; 
	
	public void connect() {
		try {
			//sock = new Socket("127.0.0.1", 5050);
			sock = new Socket("192.168.0.83", 5050);
			System.out.println("Connection to server is done");
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
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
	
	public FireResponse getFireResponse() {
		
		FireResponse f = new HitResponse();
		
		try {
			f = (FireResponse) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
}
