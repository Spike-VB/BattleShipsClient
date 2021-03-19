package com.spike.BattleShipsClient;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import com.spike.BattleShipsLib.*;

public class StartButtonListener implements ActionListener {
	
	private int FIELD_SIZE;
	private final int SHIPS_NUM = 3; 
	private Gui gui;
	private Connection con;
	private ArrayList<ShipButton> shipButtons = new ArrayList<ShipButton>();
	
	public StartButtonListener(Gui g, Connection c) {
		gui = g;
		con = c;
		shipButtons = g.getShipButtons();
		FIELD_SIZE = g.getFieldSize();
	}
	
	public void actionPerformed(ActionEvent ev) {

		JButton startButton = (JButton) ev.getSource();
		startButton.setEnabled(false);
		
		sendShips();
		gui.repaint();
		setFireButtons();
	}
	
	private void sendShips() {
		
		int[][] shipsMatrix = new int[FIELD_SIZE][FIELD_SIZE];
		
		for(ShipButton b : shipButtons) {
			if(b.isShip()) {
				shipsMatrix[b.getPosition()[0]][b.getPosition()[1]] = 1;
			}
		}
		
		Ship[] ships = new ShipsFactory(SHIPS_NUM).buildShips(shipsMatrix);
		
		con.sendShips(ships);
	}
	
	private void setFireButtons() {
		
		FireButtonListener fbl = new FireButtonListener(con);
		
		for(ShipButton b : shipButtons) {
			b.setSeaIcon();
			b.removeActionListener(b.getActionListeners()[0]);
			b.addActionListener(fbl);
		}
		
	}
	
}
