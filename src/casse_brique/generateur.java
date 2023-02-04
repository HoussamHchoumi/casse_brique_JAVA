package casse_brique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class generateur {
	public int briques[][];
	public int briquewidth;
	public int briqueheight;

	public generateur(int row, int col) {
		briques = new int[row][col];
		for(int i = 0; i < briques.length;i++) {
			for(int j=0; j< briques[0].length; j++) {
				briques[i][j] = 1;
			}
		}
		briquewidth = 540/col;
		briqueheight = 150/row;
	}
	//methode pour de tracer les briques
	public void draw(Graphics2D g) {
		for(int i = 0; i< briques.length; i++) {
			for(int j=0; j< briques[0].length; j++) {
				if(briques[i][j]>0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect( j * briquewidth + 80, i * briqueheight + 50, briquewidth, briqueheight);
					
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect( j * briquewidth + 80, i * briqueheight + 50, briquewidth, briqueheight);
					
				}
			}
		}
	}
	//methode pour moddifier la valeur des briques
	public void setbriqueValue(int value, int row, int col) {
		briques[row][col] = value;
	}

}
