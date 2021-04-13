package com.spike.BattleShipsClient;

import javax.swing.SwingUtilities;

public class BattleShipsClient {
	
	private final int FIELD_SIZE = 10;
	
    public static void main( String[] args ) {
    	BattleShipsClient bsc = new BattleShipsClient();
        bsc.startClient();
    }
    
    private void startClient() {
    	Gui gui = new Gui(FIELD_SIZE);
    	Connection con = new Connection();
    	
    	StartButtonListener sbListener = new StartButtonListener(gui, con);
    	//sbListener.setShipsNum(3);
    	gui.setStartButtonListener(sbListener);
    	
    	SwingUtilities.invokeLater(gui);
    	con.connect();
    }

}