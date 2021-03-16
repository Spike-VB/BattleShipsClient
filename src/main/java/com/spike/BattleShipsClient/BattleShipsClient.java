package com.spike.BattleShipsClient;

public class BattleShipsClient {
	
	private final int FIELD_SIZE = 10;
	private int[][] shipArray;
	
    public static void main( String[] args ) {
    	BattleShipsClient bsc = new BattleShipsClient();
        bsc.gameStart();
    }
    
    private void gameStart() {
    	Gui gui = new Gui(FIELD_SIZE);
    	Connection con = new Connection();
    	
    	StartButtonListener sbListener = new StartButtonListener(gui, con);
    	gui.setStartButtonListener(sbListener);
    	
    	gui.buildGui();
    	con.connect();
    }

}