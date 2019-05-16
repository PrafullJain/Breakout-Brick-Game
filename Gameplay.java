package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener,ActionListener{

	private boolean play=false;
	private int score=0;
	
	private MapGenerator mg;
	private int totBricks=21;
	
	private Timer time;
	private int delay=8;
	
	private int  playerX=310;
	
	private int ballposX=120;
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	
	public Gameplay() {

		mg=new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(8,this);
		time.start();
		
	}
	public void paint(Graphics g)
	{
		//Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		mg.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692,3);
		g.fillRect(691, 0, 3, 592);
		
		//Paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550, 100,8);
	
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,ballposY, 20,20);
		
		
		//set Scores
		g.setColor(Color.BLUE);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString("Score:"+score,577,30);
			
		if(ballposY>570)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Scores: "+score,190,270);
				
			g.setFont(new Font("serif",Font.BOLD,18));
			g.drawString("Press Enter to Restart: ",230,300);
		}
		
		if(totBricks==0 && score==105)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD,32));
			g.drawString("You have Won the Game",180,230);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Your Scores: "+score,230,270);
				
			g.setFont(new Font("serif",Font.BOLD,18));
			g.drawString("Press Enter to Restart: ",224,300);
		}
		//Heading
		g.setFont(new Font("LCD",Font.BOLD,44));
		g.drawString("Breakout-Brick",185,40);
				
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		time.start();
		if(play)
		{
			
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir=-ballYdir;
			}
			
		A:for(int i=0;i<mg.map.length;i++)
			{
				for(int j=0;j<mg.map[0].length;j++)
				{
					if(mg.map[i][j]>0)
					{
						int brickX=j*mg.brickwidth+80;
						int brickY=i*mg.brickheight+50;
						int brickWidth=mg.brickwidth;
						int brickHeight=mg.brickheight;
						
						Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,28,28);
						Rectangle brickRect=rect;
						
						if(ballRect.intersects(brickRect))
						{
							mg.setBrickValue(0, i, j);
							totBricks--;
							score+=5;
							
							if(ballposX+19<=brickRect.x || ballposX+1>=brickRect.x+brickRect.width)
							{
								ballXdir=-ballXdir;
							}else
							{
								ballYdir=-ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0)
			{
				ballXdir=-ballXdir;
			}
			if(ballposY<0)
			{
				ballYdir=-ballYdir;
			}
			if(ballposX>670)
			{
				ballXdir=-ballXdir;
			}
			
		}
		repaint();
	}
	public void moveRight()
	{
		play=true;
		playerX+=20;
	}
	public void moveLeft()
	{
		play=true;
		playerX-=20;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
			{
				playerX=600;
			}
			else
			{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<10)
			{
				playerX=10;
			}
			else
			{
				moveLeft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				score=0;
				totBricks=21;
				mg=new MapGenerator(3, 7);
				repaint();
				
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	
	

	

	
	

}
