package gam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


/*
 * height of orange =100
 * height of green=20
 * 
 * height of main 600
 * width of main 800
 * 
 * height of circle 120
 * 
 * width of circle 40
 * 
 * height & width of ball 20
 * 
 */
public class Rings implements ActionListener,KeyListener,MouseListener{

	public JFrame mainFrame;
	public MainPanelClass mainPanel;
	public static Rings ring;
	public int WIDTH=800,HEIGHT=700;
	public Ball ball;
	public int gameStatus=0;
	public int ticks=0;
	//public Circle circle;
	public ArrayList<Circle> column;
	
	boolean up;
	public Rings(){
		mainFrame=new JFrame("Rings");
		
		Timer timer=new Timer(20,this);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(WIDTH,HEIGHT);
		mainPanel=new MainPanelClass();
		mainFrame.add(mainPanel);
		mainFrame.addKeyListener(this);
		mainFrame.addMouseListener(this);
		mainFrame.setVisible(true);
		//mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		
		column=new ArrayList<Circle>();
		column.add(new Circle(this,0));
		column.add(new Circle(this,1));
		column.add(new Circle(this,2));
		column.add(new Circle(this,3));
		
		ball=new Ball(this);
		
		timer.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ring=new Rings();
	}
	public void update() {
		
		ticks++;
		if(up)
		{
			ball.move(true);
		}
		else
		{
			ball.move(false);
		}
		for(int i=0;i<column.size();i++)
		{
			Circle cir=(Circle)column.get(i);
			cir.update(ball,this);
		}
		
		for(int i=0;i<column.size();i++)
		{
			Circle cir=(Circle)column.get(i);
			if(cir.x<0)
			{
				column.remove(i);
				column.add(new Circle(this,i));
			}
			System.out.println(i);
		}
	}
	public void mainGraphics(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.ORANGE);
		g.fillRect(0, HEIGHT-100, WIDTH, 100);
		g.setColor(Color.GREEN);
		g.fillRect(0, HEIGHT-120, WIDTH, 20);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(10));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.drawString("SCORE==> "+ball.score, WIDTH-130, 100);
		if(gameStatus==1)
		{
			ball.drawBall(g);
			for(int i=0;i<column.size();i++)
			{
				Circle circle=(Circle)column.get(i);
				circle.drawCircle(g);
			}
		}
		else if(gameStatus==0)
		{
			g.setColor(Color.red);
			g.setStroke(new BasicStroke(10));
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("GAME PAUSED /// PRESS SPACE", WIDTH/2-100, HEIGHT/2-10);
		}
		else
		{
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(10));
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("GAME OVER /// PRESS SPACE", WIDTH/2-100, HEIGHT/2-10);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(gameStatus==1)
		{
			update();
		}
		else if(gameStatus==2)
		{
			
		}
		mainPanel.repaint();
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getKeyCode();
		if(id==KeyEvent.VK_SPACE)
		{
			up=true;
		}
		if(id==KeyEvent.VK_ESCAPE)
		{
			System.out.println("esc");
			if(gameStatus==0)
				gameStatus=1;
			else if(gameStatus==2)
			{
				gameStatus=1;
				ball.score=0;
			}
			else
				gameStatus=0;
		}
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getKeyCode();
		if(id==KeyEvent.VK_SPACE)
			up=false;
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		up=true;
		System.out.println("mi");
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		up=false;
		System.out.println("mo");
	}

}
