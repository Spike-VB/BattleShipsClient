package com.spike.BattleShipsClient;

import java.awt.event.*;
import javax.swing.*;

public class StartButtonListener implements ActionListener {
	
	int FIELD_SIZE;
	Gui gui;
	Connection con;
	JButton[][] shipButtonMatrix;
	
	public StartButtonListener(Gui g, Connection c) {
		gui = g;
		con = c;
		shipButtonMatrix = g.getShipButtonMatrix();
		FIELD_SIZE = g.getFieldSize();
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				JButton b = shipButtonMatrix[i][j];
				b.setEnabled(true);
				b.setIcon(new SeaIcon());
			}
		}
		
		gui.repaint();
	}
	
}
