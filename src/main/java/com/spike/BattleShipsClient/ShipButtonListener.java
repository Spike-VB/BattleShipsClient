package com.spike.BattleShipsClient;

import java.awt.event.*;

public class ShipButtonListener implements ActionListener {
	
	private ShipButton b; 

	public void actionPerformed(ActionEvent ev) {

		b = (ShipButton) ev.getSource();
		
		if(!b.isShip()) {
			b.setShipIcon();
			b.setShip(true);
		}
		else {
			b.setSeaIcon();
			b.setShip(false);
		}

	}

}
