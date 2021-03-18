package com.spike.BattleShipsClient;

import java.io.Serializable;
import java.util.*;

public class Ship implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	ArrayList<int[]> ship = new ArrayList<int[]>();
	
	public Ship() {
		
	}
	
	public Ship(int[][] s) {
		for(int[] i : s) {
			buildShip(i[0], i[1]);
		}
	}
	
	public void buildShip(int i, int j) {
		int[] part = {i, j};
		ship.add(part);
	}
	
	public ArrayList<int[]> getShip() {
		return ship;
	}
	
	public void printShip() {
		System.out.print(toString());
	}
	
	public String toString() {
		String string = "";
		
		for(int[] sh : ship) {
			string = string + sh[0] + "," + sh[1] + " ";
		}
		
		string = string.trim();
		
		return string;
	}
}
