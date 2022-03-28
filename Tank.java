import javafx.scene.shape.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate; 

class Tank extends Sprite
{
	public static final double body = 30;
	public static final double length = 32;

	void updateSprite()
	{
		if (active)
		{
			updatePosition();
		}
	}

	void render(GraphicsContext gc, int player)
	{
		if (visible)
		{
		    //player 1 code
			if (player == 1) {
				//Create tank body
				gc.setFill(Color.BLUE);
				gc.fillRoundRect(x, y, body, body, body/2, body/2);
		        
		        //Save the state of the GC for affine rotations
			    gc.save();
			    //Rotate barrel around the center point of the body by angle "th"
			    gc.transform(new Affine(new Rotate(th, x + body/2, y + body/2)));
			    //Build tank barrel
				gc.fillRoundRect(x + (body - length/4)/2, y + (body - length/4)/2, length, length/4, length/4, length/4);
				gc.setStroke(Color.BLACK);
				gc.strokeRoundRect(x + (body - length/4)/2, y + (body - length/4)/2, length, length/4, length/4, length/4);
				//Restore state of GC with transformations
				gc.restore(); 
				
				
			} //player 2 code (not yet implemented)
			else if (player == 2) { 
				gc.setFill(Color.RED);
				gc.fillRoundRect(x, y, body, body, 4, 4);
			}
		}
	}
}
