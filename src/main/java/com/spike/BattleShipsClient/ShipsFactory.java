package com.spike.BattleShipsClient;

public class ShipsFactory {
	
	Ship[] ships = new Ship[10];
	int numOfShips = 0;
	
	public Ship[] buildShips(int[][] shipsMatrix) {
		
		int[][] extMatrix = new int[shipsMatrix.length + 1][shipsMatrix[0].length + 1];
		
		for(int i = 0; i < shipsMatrix.length; i++) {
			for(int j = 0; j < shipsMatrix[i].length; j++) {
				extMatrix[i][j] = shipsMatrix[i][j];
			}
		}
		
		for(int i = 0; i < extMatrix.length - 1; i++) {
			for(int j = 0; j < extMatrix[i].length - 1; j++) {
				
				if(extMatrix[i][j] == 1) {
					extMatrix[i][j] = 2;
					Ship s = new Ship();
					ships[numOfShips] = s;
					numOfShips++;
					s.buildShip(i, j);
					
					int k = 1;
					
					if(extMatrix[i][j + k] == 1) {	
						while(extMatrix[i][j + k] == 1) {
							extMatrix[i][j + k] = 2;
							s.buildShip(i, j + k);
							k++;
						}
					}
					else if(extMatrix[i + k][j] == 1) {
						while(extMatrix[i + k][j] == 1) {
							extMatrix[i + k][j] = 2;
							s.buildShip(i + k, j);
							k++;
						}
					}
					
				}
			}
		}

		return ships;
	}


}
