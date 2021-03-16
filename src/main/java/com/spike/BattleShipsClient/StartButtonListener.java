package com.spike.BattleShipsClient;

import java.awt.event.*;
import javax.swing.*;

public class StartButtonListener implements ActionListener {
	
	private int FIELD_SIZE;
	private Gui gui;
	private Connection con;
	private ShipButton[][] shipButtonMatrix;
	private SeaIcon seI = new SeaIcon();
	
	public StartButtonListener(Gui g, Connection c) {
		gui = g;
		con = c;
		shipButtonMatrix = g.getShipButtonMatrix();
		FIELD_SIZE = g.getFieldSize();
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		int[][] shipMatrix = new int[FIELD_SIZE][FIELD_SIZE];
		
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				ShipButton b = shipButtonMatrix[i][j];
				b.setEnabled(true);
				b.setIcon(seI);
				
				if(b.isShip()) {
					shipMatrix[i][j] = 1;
				}
			}
		}
		
		gui.repaint();
		
		con.sendShips(shipMatrix);
	}
	
	private void setFireButtons() {
		
	}
	
}
