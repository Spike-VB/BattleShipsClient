package com.spike.BattleShipsClient;

import java.awt.*;
import javax.swing.Icon;

public class KilledIcon implements Icon {
	
	private int height;
	private int width;

	public int getIconHeight() {
		return height;
	}

	public int getIconWidth() {
		return width;
	}

	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
		height = arg0.getHeight();
		width = arg0.getWidth();
		
		g.setColor(Color.black);
        g.fillRect(0, 0, width, height);		
	}
	
}