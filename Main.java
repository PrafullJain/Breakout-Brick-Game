package brickBreaker;

import java.awt.Toolkit;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
	JFrame jfobj=new JFrame();
	Gameplay gp=new Gameplay();
	jfobj.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-300,Toolkit.getDefaultToolkit().getScreenSize().height/2-300,700,600);
	jfobj.setTitle("Breakout Brick");
	jfobj.setIconImage(Toolkit.getDefaultToolkit().getImage("src/brickBreaker/logo.png"));
	jfobj.setVisible(true);
	jfobj.setResizable(false);
	jfobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jfobj.add(gp);
	}
}
