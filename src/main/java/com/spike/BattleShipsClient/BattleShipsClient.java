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
    
    /*
	private void fillShipArray() {
		boolean done = false;
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				if(shipButtonMatrix[i][j].equals(b)) {
					shipArray[i][j] = 1;
					done = true;
				}
				if(done) {break;}
			}
			if(done) {break;}
		}
	}
	*/

}