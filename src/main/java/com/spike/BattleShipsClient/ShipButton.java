package com.spike.BattleShipsClient;

import javax.swing.*;

public class ShipButton extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean ship = false;
	private int[] position = new int[2];
	
	private SeaIcon seI = new SeaIcon();
	private ShipIcon shI = new ShipIcon();
	private FireIcon fiI = new FireIcon();
	
	public boolean isShip() {
		return ship;
	}
	
	public void setShip(boolean s) {
		ship = s;
	}
	
	public void setPosition(int i, int j) {
		position[0] = i;
		position[1] = j;
	}
	
	public int[] getPosition() {
		return position;
	}
	
	public void setSeaIcon() {
		this.setIcon(seI);
	}
	
	public void setShipIcon() {
		this.setIcon(shI);
	}
	
	public void setFireIcon() {
		this.setIcon(fiI);
	}
	
}
