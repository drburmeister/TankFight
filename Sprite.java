import javafx.scene.canvas.GraphicsContext;
import java.lang.*;

class Sprite
{
	static int v = 3;
	double x,y,th,hth;
	double vx = 0, vy = 0, dx = 1, dy = 1;
	int hit = 0;
	boolean active=false, visible=false, up = false, left = false, right = false, down = false, clock = false, Cclock = false;
	
	void updateTank(int player)
	{
		//All movement reactions for player inputs
		if (up && y > 0) y-=v;
		if (left && x > 0) {
			if (player == 2 && x > tankFight.WIDTH/2 + Tank.body - wall.size/4) x-=v;
			else if (player == 1) x-=v;
		}
		if (right && x < (tankFight.WIDTH - Tank.body)) {
			if (player == 1 && x < tankFight.WIDTH/2 - Tank.body - wall.size/4) x+=v;
			else if (player == 2) x+=v;
		}
		if (down && y < (tankFight.HEIGHT - Tank.body)) y+=v;
		if (Cclock) th-=v;
		if (clock) th+=v;
	}
	
	void fire(Sprite[] walls) {
	
		//Collision detection between bullets and player walls
		for(int i = 0; i < walls.length; i++) {
		    double dx = x-walls[i].x;
		    double dy = y-walls[i].y;
		    boolean test = dx*dx+dy*dy < wall.size*wall.size;
			if (test && walls[i].isActive()) {
				if((x > walls[i].x - wall.size/2  && x < walls[i].x + wall.size/2)) { //hitting top or bottom wall of block
					vy = -vy;
				}
				else if((y > walls[i].y - wall.size/2  && y < walls[i].y + wall.size/2)) { //hitting left or right wall of block
					vx = -vx;
				}
				hit++;
				walls[i].suspend();
				tankFight.bullWall.play(); //Play Sound
			}
		}
		//Handles the moving of a bullet when fired should bounce at edges
		if ((x < bullet.rad && vx < 0) || (x + bullet.rad > tankFight.WIDTH && vx > 0)) {
			vx = -vx; //reverse x velocity if it his a vertical wall
			hit++; //increase hit counter
		}
		else if ((y < bullet.rad && vy < 0) || (y + bullet.rad > tankFight.HEIGHT && vy > 0)) {
			vy = -vy; //reverse y velocity if it his a vertical wall
			hit++;
		}
		else {
			x += vx; //update posistion based on velocity
			y += vy;
		}
		if (hit >= 3) { //if ball bounces 3 times it should reset variables
			active = false; 
			visible = false; 
			hit = 0; 
			dx = 1;
			dy = 1;
		}
	}
	void setPosition(double x2, double y2) //Method to initialize/set coordinates of sprites
	{
		x = x2; y = y2;
	}
	boolean isHit(Sprite b) //check for tank on bullet collision
	{
		if ((((b.x <= (x + Tank.body)) && ((b.x+bullet.rad) >= x)) && ((b.y <= (y+Tank.body)) && ((b.y+bullet.rad) >= y))))
			return true;
		return false;
	}
	boolean wallOverlap(Sprite b) //check if player walls are overlapping
	{
		if ((((b.x <= (x + wall.size)) && ((b.x+wall.size) >= x)) && ((b.y <= (y+wall.size)) && ((b.y+wall.size) >= y))))
			return true;
		return false;
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
}