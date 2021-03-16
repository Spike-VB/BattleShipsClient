package com.spike.BattleShipsClient;

import javax.swing.*;

public class ShipButton extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean ship = false;
	
	public ShipButton(Icon i) {
		super(i);
	}
	
	public boolean isShip() {
		return ship;
	}
	
	public void setShip(boolean s) {
		ship = s;
	}

}
