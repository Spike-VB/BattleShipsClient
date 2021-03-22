package com.spike.BattleShipsClient;

import java.awt.event.*;

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
		con.sendFire(b.getPosition());
		b.setBlock(true);
		b.setEmptyIcon();
		gui.blockShipButtons();
	}

}
