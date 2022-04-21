import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class wall extends Sprite
{
	public static final double size = 28;
	
	void render(GraphicsContext gc, int type)
	{
	    if (visible)
	    { //Create bullet sprite
	    	if (type == 0) {
		    	gc.setFill(Color.CORNSILK);
		    	gc.fillRect(x-size/2, y-size/2, size, size);
		    	gc.setStroke(Color.BLACK);
		    	gc.strokeRect(x-size/2, y-size/2, size, size);
	    	}
	    	
	    	if (type == 1) {
		    	gc.setFill(Color.BLACK);
		    	gc.fillRect(x-size/2, y-size/2, size, size);
		    	gc.setStroke(Color.CORNSILK);
		    	gc.strokeRect(x-size/2, y-size/2, size, size);
	    	}
	    	if (x > tankFight.WIDTH || x < 0 || y > tankFight.HEIGHT || y < 0) suspend();
	    	
	    }
	}

}