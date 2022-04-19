import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class wall extends Sprite
{
	public static final double size = 28;
	
	void render(GraphicsContext gc)
	{
	    if (visible)
	    { //Create bullet sprite
	    	gc.setFill(Color.RED);
	    	gc.fillRect(x-size/2, y-size/2, size, size);
	    	gc.setStroke(Color.BLACK);
	    	gc.strokeRect(x-size/2, y-size/2, size, size);
	    }
	}

}