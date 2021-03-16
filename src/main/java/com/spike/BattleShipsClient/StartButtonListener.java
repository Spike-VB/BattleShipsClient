package com.spike.BattleShipsClient;

import java.awt.event.*;
import javax.swing.*;

public class StartButtonListener implements ActionListener {
	
	private int FIELD_SIZE;
	private Gui gui;
	private Connection con;
	private JButton[][] shipButtonMatrix;
	private SeaIcon seI = new SeaIcon();
	
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
				b.setIcon(seI);
			}
		}
		
		gui.repaint();
	}
	
}
