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
		
		FireResponseReader frr = new FireResponseReader(gui, con, b);
		frr.execute();
		
		/*
		
		SwingWorker<FireResponse, Void> worker = new SwingWorker<FireResponse, Void>() {

			@Override
			public FireResponse doInBackground() {
				return con.getFireResponse();
			}
			
			public void done() {
					try {
					FireResponse f = get();

					if(f instanceof HitResponse) {
						HitResponse h = (HitResponse) f;
						
						if(h.isHit()) {
							gui.unblockShipButtons();
							b.setFireIcon();
						}
						else {
							b.setEmptyIcon();
						}
					}
					else {
						WaitingResponse w = (WaitingResponse) f;
						gui.updateFireArray(w.getPosition());
						
						if(!w.isHit()) {
							gui.unblockShipButtons();
						}
					}
					
					gui.repaint();
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		
		worker.execute();
		*/

	}

}
