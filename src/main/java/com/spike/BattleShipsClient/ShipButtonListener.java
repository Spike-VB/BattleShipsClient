package com.spike.BattleShipsClient;

import java.awt.event.*;

public class ShipButtonListener implements ActionListener {
	
	private Gui gui;
	private ShipButton b;
	
	public ShipButtonListener(Gui g) {
		gui = g;
	}

	public void actionPerformed(ActionEvent ev) {

		b = (ShipButton) ev.getSource();
		
		if(!b.isShip()) {
			b.setShipIcon();
			b.setShip(true);
			dispEmptyCells(b);
		}
		else {
			b.setSeaIcon();
			b.setShip(false);
			removeEmptyCells();
		}

	}
	
	private void dispEmptyCells(ShipButton sb) {
		ShipButton tempB;
		int top = sb.getPosition()[0] == 0 ? 1 : -1;
		int bottom = sb.getPosition()[0] == gui.getFieldSize() - 1 ? 0 : 2;
		int left = sb.getPosition()[1] == 0 ? 1 : -1;
		int right = sb.getPosition()[1] == gui.getFieldSize() - 1 ? 0 : 2;

		for(int i = top; i < bottom; i = i + 2) {
			for(int j = left; j < right; j = j + 2) {
				tempB = gui.getShipButtons().get((sb.getPosition()[0] + i) * gui.getFieldSize() + sb.getPosition()[1] + j);
				if(tempB.getIcon() instanceof SeaIcon) {
					tempB.setEmptyIcon();
					tempB.setEnabled(false);
				}
			}
		}
	}
	
	private void removeEmptyCells() {
		ShipButton tempB;
		int top = b.getPosition()[0] == 0 ? 1 : -1;
		int bottom = b.getPosition()[0] == gui.getFieldSize() - 1 ? 1 : 2;
		int left = b.getPosition()[1] == 0 ? 1 : -1;
		int right = b.getPosition()[1] == gui.getFieldSize() - 1 ? 1 : 2;

		for(int i = top; i < bottom; i = i + 2) {
			for(int j = left; j < right; j = j + 2) {
				tempB = gui.getShipButtons().get((b.getPosition()[0] + i) * gui.getFieldSize() + b.getPosition()[1] + j);
				if(tempB.getIcon() instanceof EmptyIcon) {
					tempB.setSeaIcon();
					tempB.setEnabled(true);
				}
			}
		}
		
		for(ShipButton sb : gui.getShipButtons()) {
			if(sb.isShip()) {
				dispEmptyCells(sb);
			}
		}
	}

}
