package com.spike.BattleShipsClient;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import com.spike.BattleShipsLib.*;

public class StartButtonListener implements ActionListener {
	
	private int FIELD_SIZE;
	private int SHIPS_NUM = 10; 
	private Gui gui;
	private Connection con;
	private ArrayList<ShipButton> shipButtons = new ArrayList<ShipButton>();
	int[] fieldArray;
	Ship[] ships;
	
	public StartButtonListener(Gui g, Connection c) {
		gui = g;
		con = c;
		shipButtons = g.getShipButtons();
		FIELD_SIZE = g.getFieldSize();
		fieldArray = new int[FIELD_SIZE * FIELD_SIZE];
	}
	
	public void setShipsNum(int num) {
		SHIPS_NUM = num;
	}
	
	public int getShipsNum() {
		return SHIPS_NUM;
	}
	
	public void actionPerformed(ActionEvent ev) {
		JButton startButton = (JButton) ev.getSource();
		buildShips();
		boolean start = true;

		if(!shipsNumCheck() || !shipsLengthCheck()) {
			startButton.setText("Number or length of ships is wrong");
			start = false;
			
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					startButton.setEnabled(false);
					try {
			    		Thread.sleep(3000);
			    	}
			    	catch(InterruptedException ex) {
			    		ex.printStackTrace();
			    	}
					return null;
				}
				
				@Override
				protected void done() {
					startButton.setEnabled(true);
					startButton.setText("Start");
				}
			};
			
			worker.execute();
		}
		
		if(start) {
			gui.setField(fieldArray);
			startButton.setEnabled(false);
			sendShips(ships);
			setFireButtons();
		}
		
		gui.repaint();
	}
	
	private void buildShips() {
		int[][] shipsMatrix = new int[FIELD_SIZE][FIELD_SIZE];
		
		int i = 0;
		for(ShipButton b : shipButtons) {
			if(b.isShip()) {
				shipsMatrix[b.getPosition()[0]][b.getPosition()[1]] = 1;
				fieldArray[i] = 1;
			}
			else {
				fieldArray[i] = 0;
			}
			i++;
		}
		
		ships = new ShipsFactory(SHIPS_NUM).buildShips(shipsMatrix);
	}
	
	private void sendShips(Ship[] ships) {
		con.sendShips(ships);
	}
	
	private void setFireButtons() {
		FireButtonListener fbl = new FireButtonListener(gui, con);
		
		for(ShipButton b : shipButtons) {
			b.setEnabled(true);
			b.setSeaIcon();
			b.removeActionListener(b.getActionListeners()[0]);
			b.addActionListener(fbl);
			b.setShip(false);
		}
	}
	
	private boolean shipsLengthCheck() {
		int[] counter = new int[4];
		int[] expected = {4, 3, 2, 1};
		
		for(Ship s : ships) {
			switch(s.getShip().size()) {
			case(1):
				counter[0]++;
				break;
			case(2):
				counter[1]++;
				break;
			case(3):
				counter[2]++;
				break;
			case(4):
				counter[3]++;
				break;
			}
		}
		
		for(int i = 0; i < expected.length; i++) {
			if(counter[i] != expected[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean shipsNumCheck() {
		for(int i = 0; i < ships.length; i++) {
			if(ships[i] == null) {
				return false;
			}
		}
		return true;
	}
}
