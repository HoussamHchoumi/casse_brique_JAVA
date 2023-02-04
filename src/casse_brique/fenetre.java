package casse_brique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class fenetre extends JPanel implements KeyListener , ActionListener{
	
	private boolean play = false;
	private int score = 0;
	private int totalBrique = 21;
	private Timer timer;
	private int delay = 5;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private generateur briques;
	
	

	public fenetre(Timer Timer) {
		briques = new generateur(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//backgrounds
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		
		//murs
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//dessin des briques 
		briques.draw((Graphics2D)g);
		
		//la raquette
		g.setColor(Color.GRAY);
		g.fillRect(playerX, 550, 100, 8);
		
		//score 
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//ball
		g.setColor(Color.RED);
		g.fillOval(ballposX, ballposY, 20, 20);
		//condition si vous avez gagné
		if(totalBrique<= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("vous avez gagné",260,300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("cliquer sur entrer pour rejouer", 230,350);
		}
		
		//une condition si vous avez perdu 
		if(ballposY> 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("vous avez perdu", 190,300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("cliquer sur entrer pour rejouer", 230,350);
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX,550,100,5))){
				ballYdir = -ballYdir;
			}
			A: for(int i = 0;i< briques.briques.length; i++) {
				for(int j = 0; j< briques.briques[0].length; j++) {
					if(briques.briques[i][j]> 0) {
						int briqueX = j * briques.briquewidth + 80;
						int briqueY = i * briques.briqueheight + 50;
						int briquewidth = briques.briquewidth;
						int briqueheight = briques.briqueheight;
						
						Rectangle rect = new Rectangle(briqueX, briqueY, briquewidth, briqueheight);
						Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
						Rectangle briqueRect = rect;
						
						if(ballRect.intersects(briqueRect)) {
							briques.setbriqueValue(0, i, j);
							totalBrique--;
							score +=5;
							
							if(ballposX + 19 <= briqueRect.x || ballposX + 1 >= briqueRect.x + briqueRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0) {
				ballXdir= -ballXdir;
			}
			if(ballposY<0) {
				ballYdir= -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir= -ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//deplacement de la raqutte vers l'adroite
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >=600) {
				playerX = 600;
			}else {moveRight();}
		}
		//deplacement de la raqutte vers l'agauche
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <10) {
				playerX = 10;
			}else {moveLeft();}
		}
		//cliquer sur entrer pour rejouer
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBrique = 21;
				briques = new generateur(3, 7);
				
				repaint();
			}
		}
		
	}
	//methode de deplacement adroite
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	//methode de deplacement agauche
	public void moveLeft() {
		play = true;
		playerX-=20;
	}

	

}
