package com.spike.BattleShipsClient;

import junit.framework.TestCase;
import com.spike.BattleShipsLib.*;

public class ShipsFactoryTest extends TestCase {
	
	private int[][] testMatrix = {{0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 0, 1, 1}};
	private String expectedString = "0,1 0,2 0,3 0,4; 2,1 3,1; 4,3 4,4";
	private ShipsFactory factory = new ShipsFactory(3);
	
	public void testStart() {
		printMatrix(testMatrix);
		
		System.out.println("");
		
		Ship[] ships = factory.buildShips(testMatrix);
		
		String st = "";
		
		for(Ship s : ships) {
			st = st + s.toString() + "; ";
		}
		
		st = st.substring(0, st.length() - 2);

		assertEquals(expectedString, st);
	}
	
	private void printMatrix(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
