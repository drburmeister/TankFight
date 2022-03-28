import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class bullet extends Sprite
{
	public static final double rad = 4;

	void updateSprite()
	{
		if (active)
		{
			fire();
		}
	}

	void render(GraphicsContext gc)
	{
	    if (visible)
	    {
	      gc.setFill(Color.WHITE);
	      gc.fillOval(x-rad, y-rad, 2*rad, 2*rad);
	      gc.setStroke(Color.BLUE);
	      gc.strokeOval(x-rad, y-rad, 2*rad, 2*rad);
	    }
	}

}