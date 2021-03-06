package tanwar;

import  java.awt.*;
import java.awt.event.KeyEvent;

public class Tank 
{
	TankClient tc;
	
	private boolean  live=true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	private  boolean  good;
	private Direction ptDir=Direction.L; 
	public static final int WIDTH=30;
	public  static final int HEIGHT=30;
	
	public static final int XSPEED=5;
	public static final int YSPEED=5;
	private int x,y;
	private  Direction  dir=Direction.STOP;  
	enum  Direction{L,  U, R, D,  STOP};
	private  boolean  bL=false,bU=false,bR=false,bD=false;
	
	
	
	public Tank(int x, int y,boolean good) 
	{
	
		this.x = x;
		this.y = y;
		this.good=good;
	}
	public Tank(int x,int y, boolean good, TankClient  tc)
	{
		this(x,y,good);
		this.tc=tc;
	}
	public  void draw(Graphics g)
	{    
	   if(!live)return;
		Color  c= g.getColor();
		if(good )g.setColor(Color.RED);
		else  g.setColor(Color.BLUE);
		g.fillOval(x,y,WIDTH,HEIGHT);
		g.setColor(c);
		switch(ptDir)
		{
		case L:
			g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x,y+Tank.HEIGHT/2);
			break;
		
		case U:
			g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y);
			break;
		
		case R:
			g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y+Tank.HEIGHT/2);
			break;
		
		case D:
		
			g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y+Tank.HEIGHT);
			break;
		
		
		}
		move();

	}
	
	void move()
	{
		switch(dir)
		{
		case L:
			x -=XSPEED;
			break;
		
		case U:
			y -=YSPEED;
			break;
		
		case R:
			x +=XSPEED;
			break;
		
		case D:
		
			y +=YSPEED;
			break;
		
		case  STOP:
			break;
		}
		if(this.dir!=Direction.STOP){
			this.ptDir=this.dir;
		}
	}
	public  void keyPressed(KeyEvent e)
	{
		
		int  key =e.getKeyCode();
		switch(key)
		{
		
		case  KeyEvent.VK_LEFT :
			bL=true;
			break;
		case  KeyEvent.VK_RIGHT :
			bR=true;
			break;
		case  KeyEvent.VK_UP :
			bU=true;
			break;
		case  KeyEvent.VK_DOWN :
			bD=true;
			break;
		
		}
		locationDirection();
	
	}
	
	
	public void keyReleased(KeyEvent e)
	{
		int  key =e.getKeyCode();
		switch(key){
		case KeyEvent.VK_CONTROL:
			fire(); 
			break;
		case  KeyEvent.VK_LEFT :
			bL=false;
			break;
		case  KeyEvent.VK_RIGHT :
			bR=false;
			break;
		case  KeyEvent.VK_UP :
			bU=false;
			break;
		case  KeyEvent.VK_DOWN :
			bD=false;
			break;
		
		
 			}
		locationDirection();
	}
	void  locationDirection()
	{
		if(bL && !bU && !bR && !bD)  dir=Direction.L;
	
		else if(!bL && bU && !bR && !bD)  dir=Direction.U;
	
		else if(!bL && !bU && bR && !bD)  dir=Direction.R;
	
		else if(!bL && !bU && !bR && bD)  dir=Direction.D;

		else if(!bL && !bU && !bR && !bD)  dir=Direction.STOP;
		
	}
	public Missile fire()
	{  int x=this.x+Tank.WIDTH/2-Missile.WIDTH/2;
	   int y=this.y+Tank.HEIGHT/2-Missile.HEIGHT/2;
		Missile m=new Missile(x,y,ptDir);
		tc.missiles.add(m);
		return  m;
	}
	public  Rectangle  getRect(){
		  return  new Rectangle(x,y,WIDTH,HEIGHT);
	  }
}
