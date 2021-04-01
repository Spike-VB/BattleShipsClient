package com.spike.BattleShipsClient;

import java.awt.event.*;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

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
		b.setWaitingIcon();
		gui.blockShipButtons();
		con.sendFire(b.getPosition());
		
		SwingWorker<FireResponse, Void> worker = new SwingWorker<FireResponse, Void>() {

			@Override
			public FireResponse doInBackground() {
				return con.getFireResponse();
			}
			
			public void done() {
				FireResponse f = new FireResponse();
				try {
					f = get();
					/*
					if(f.getTurn() && f.isHit()) {
						b.setFireIcon();
					}
					else {
						b.setEmptyIcon();
					}
					gui.updateFireArray(f.getPosition());
					gui.unblockShipButtons();
					*/
					if(f.getTurn()) {
						
						gui.unblockShipButtons();
						
						if(f.isHit()) {
							b.setFireIcon();
						}
						else {
							b.setEmptyIcon();
							gui.updateFireArray(f.getPosition());
						}
					}
					else {
						gui.updateFireArray(f.getPosition());
					}
					
					gui.repaint();
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		
		worker.execute();
	}

}
