package com.spike.BattleShipsClient;

import java.awt.event.*;

public class ShipButtonListener implements ActionListener {
	
	private ShipIcon shI = new ShipIcon();
	private SeaIcon seI = new SeaIcon();
	private ShipButton b; 

	public void actionPerformed(ActionEvent ev) {

		b = (ShipButton) ev.getSource();
		
		if(!b.isShip()) {
			b.setIcon(shI);
			b.setShip(true);
		}
		else {
			b.setIcon(seI);
			b.setShip(false);
		}

	}

}
