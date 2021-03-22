package com.spike.BattleShipsClient;

import java.awt.event.*;

import com.spike.BattleShipsLib.*;

public class FireButtonListener implements ActionListener{
	
	private ShipButton b;
	private Gui gui;
	private Connection con;
	
	public FireButtonListener(Gui g, Connection c) {
		gui = g;
		con = c;
	}

	public void actionPerformed(ActionEvent ev) {
		
		b = (ShipButton) ev.getSource();
		b.setBlock(true);
		b.setEmptyIcon();
		System.out.println("Empty");
		b.repaint();
		gui.blockShipButtons();
		gui.repaint();
		con.sendFire(b.getPosition());
		
		con.getFireResponse();

			
	}

}
