import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class bullet extends Sprite
{
	public static final double rad = 4;

	void updateSprite(Sprite[] walls)
	{
		if (active) {
			fire(walls); //Moves the bullets when active
		}
	}

	void render(GraphicsContext gc, int player)
	{
	    if (visible)
	    { //Create bullet sprite
	    	if (player == 1) {
		    	gc.setFill(Color.WHITE);
		    	gc.fillOval(x-rad, y-rad, 2*rad, 2*rad);
		    	gc.setStroke(Color.BLUE);
		    	gc.strokeOval(x-rad, y-rad, 2*rad, 2*rad);
	    	}
	    	else if (player == 2) {
		    	gc.setFill(Color.WHITE);
		    	gc.fillOval(x-rad, y-rad, 2*rad, 2*rad);
		    	gc.setStroke(Color.RED);
		    	gc.strokeOval(x-rad, y-rad, 2*rad, 2*rad);
	    	}
	    }
	}

}