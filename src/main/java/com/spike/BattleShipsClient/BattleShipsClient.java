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
    	gui.buildGui();
    	shipArray = gui.getShipArray();
    	
    	for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				System.out.print(shipArray[i][j] + " ");
			}
			System.out.println("");
		}
    }

}