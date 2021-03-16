package com.spike.BattleShipsClient;

import java.net.*;

public class Connection {
	
	Socket sock;
	
	public void connect() {
		try {
			//sock = new Socket("127.0.0.1", 5050);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendShips(int[][] ships) {
		
	}
	
}
