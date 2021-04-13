package com.spike.BattleShipsClient;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.spike.BattleShipsLib.*;

public class FireResponseReader extends SwingWorker<FireResponse, Void> {
	
	private ShipButton b;
	private Gui gui;
	private Connection con;
	
	public FireResponseReader(Gui g, Connection c, ShipButton sb) {
		gui = g;
		con = c;
		b = sb;
	}
	
	@Override
	public FireResponse doInBackground() {
		return con.getFireResponse();
	}
	
	@Override
	public void done() {
			try {
			FireResponse f = get();

			if(f instanceof HitResponse) {
				HitResponse h = (HitResponse) f;
				gui.updateFirstShot();
				
				if(h.isHit()) {
					gui.unblockShipButtons();
					b.setFireIcon();
					if(h.isKilled()) {
						gui.dispKilledShip(b.getPosition());
					}
				}
				else {
					b.setEmptyIcon();
					FireResponseReader frr = new FireResponseReader(gui, con, b);
					frr.execute();
				}
			}
			else {
				WaitingResponse w = (WaitingResponse) f;
				gui.updateFireArray(w.getPosition());
				
				if(w.isHit() || gui.getFirsShot()) {
					if(!w.isHit()) {
						gui.updateFirstShot();
					}
					FireResponseReader frr = new FireResponseReader(gui, con, b);
					frr.execute();
				}
				else {
					gui.unblockShipButtons();
				}
			}
			
			gui.repaint();
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
