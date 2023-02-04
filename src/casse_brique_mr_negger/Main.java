package casse_brique_mr_negger;

import javax.swing.JFrame;

public class Main {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();
		fenetre fenetre = new fenetre(null);
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("casse brique");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(fenetre);

	}

}
