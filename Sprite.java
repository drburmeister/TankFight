import javafx.scene.canvas.GraphicsContext;
import java.lang.*;

class Sprite
{
	static final double v = 10;
	double x,y,th,hth;
	boolean active=false, visible=false, up = false, left = false, right = false, down = false, clock = false, Cclock = false;
	
	void updatePosition()
	{
		if (up) y-=v;
		if (left) x-=v;
		if (right) x+=v;
		if (down) y+=v;
		if (Cclock) th-=v;
		if (clock) th+=v;
	}
	void fire() {
		if (x > 0 && y > 0 && x < 600 && y < 600) {
			x += (v*Math.cos(Math.toRadians(hth)));
			y += (v*Math.sin(Math.toRadians(hth)));
		}
		else 
		{
			active = false; visible = false;
		}
		
	}
	void setPosition(double x2, double y2)
	{
		x = x2; y = y2;
	}
	boolean isCloserThan(Sprite s, double dist)
	{
		// Return true if Sprite s is closer to
		// the current Sprite (this) than specified
		// distance dist.
		double dx = x-s.x;
		double dy = y-s.y;
		return dx*dx+dy*dy < dist*dist;
	}

	boolean isActive()
	{
		return active;
	}

	void suspend()
	{
		active = false; visible = false;
	}

	void resume()
	{
		active = true; visible = true;
		setPosition(x, y);
	}
	
	void shoot() {
						
	}
}