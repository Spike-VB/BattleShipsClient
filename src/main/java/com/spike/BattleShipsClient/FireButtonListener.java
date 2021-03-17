package com.spike.BattleShipsClient;

import java.awt.event.*;

public class FireButtonListener implements ActionListener{
	
	private ShipButton b; 
	private Connection con;
	
	public FireButtonListener(Connection c) {
		con = c;
	}

	public void actionPerformed(ActionEvent ev) {
		
		b = (ShipButton) ev.getSource();
		con.sendFire(b.getPosition());
		
		b.setEnabled(false);
		b.setEmptyIcon();
	}

}
