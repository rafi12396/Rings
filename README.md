package gam;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	int x,y,bHeight,bWidth,upSpeed=-2,score=0,downSpeed=1,dy=0;
	public Ball(Rings ring){
		x=200;
		y=(ring.HEIGHT-120)/2-10;
		bHeight=20;
		bWidth=20;
	}
	public void move(boolean up) {
		if(up)
		{
			upSpeed=-2;
		}
		else
		{
			upSpeed=0;
		}
		dy+=downSpeed+upSpeed;
		
		if(dy<-10)
			dy=-10;
		else if(dy>8)
			dy=8;
		y+=dy;
		if(y>Rings.ring.HEIGHT-120){
			y=Rings.ring.HEIGHT-120;
			dy=0;
		}
		else if(y<0){
			y=0;
			dy=0;
		}
	}
	public void drawBall(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, bWidth, bHeight);
	}
}
