package com.spike.BattleShipsClient;

import javax.swing.SwingUtilities;

public class BattleShipsClient {
	
	private final int FIELD_SIZE = 10;
	
    public static void main( String[] args ) {
    	BattleShipsClient bsc = new BattleShipsClient();
        bsc.gameStart();
    }
    
    private void gameStart() {
    	Gui gui = new Gui(FIELD_SIZE);
    	Connection con = new Connection();
    	
    	StartButtonListener sbListener = new StartButtonListener(gui, con);
    	gui.setStartButtonListener(sbListener);
    	
    	SwingUtilities.invokeLater(gui);
    	con.connect();
    }

}