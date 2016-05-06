package ass04.sol;

import ass04.*;
import static ass04.TextLib.Color;

public class BouncingWord extends Thread {
	
	private P2d pos;
	private int oldX, oldY;
	private String text;
	private V2d direction;
	private float speed;
	private Screen screen;
	private long period = 20; /* 20 ms = 50 frame al secondo */
	private String blank;
	private Color color;
	
	public BouncingWord(String text, int x, int y, V2d direction, float speed, Color c, Screen s){
		this.text = text;
		this.pos = new P2d(x,y);
		this.direction = direction;
		this.speed = speed;
		this.screen = s;
		this.color = c;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); i++){
			sb.append(" ");
		}
		this.blank = sb.toString();
	}
	
	public void run(){
		TextLib lib = TextLibFactory.getInstance();
		long lastTime = System.currentTimeMillis();
	
		this.oldX = Math.round(this.pos.getX());
		this.oldY = Math.round(this.pos.getY());;
		
		while (true){
			long current = System.currentTimeMillis();
			int elapsed = (int)(current - lastTime);
			
			this.pos = this.pos.sum(this.direction.mul(this.speed*elapsed));
			checkBounds();
			
			lib.writeAt(this.oldX, this.oldY, this.blank);
			int x = Math.round(this.pos.getX());
			int y = Math.round(this.pos.getY());
			lib.writeAt(x, y, this.text, this.color);
			this.oldX = x;
			this.oldY = y;
			
			long dt = System.currentTimeMillis() - current;
			if (dt < this.period){
				waitTime(this.period-dt);
			}
			lastTime = current;
		}
	}
	
	
	private void waitTime(long ms){
		try {
			Thread.sleep(ms);
		} catch (Exception ex){}
	}
	
	private void checkBounds(){
		if (this.pos.getX() < this.screen.getMinX()){
			this.pos = new P2d(this.screen.getMinX(), this.pos.getY());
			this.direction = new V2d(-this.direction.getX(), this.direction.getY());
		} else if (this.pos.getX() > this.screen.getMaxX()){
			this.pos = new P2d(this.screen.getMaxX(), this.pos.getY());
			this.direction = new V2d(-this.direction.getX(), this.direction.getY());
		} 
		
		if (this.pos.getY() < this.screen.getMinY()){
			this.pos = new P2d(this.pos.getX(),this.screen.getMinY());
			this.direction = new V2d(this.direction.getX(), -this.direction.getY());
		} else if (this.pos.getY() > this.screen.getMaxY()){
			this.pos = new P2d(this.pos.getX(),this.screen.getMaxY());
			this.direction = new V2d(this.direction.getX(), -this.direction.getY());
		} 
	}

}
