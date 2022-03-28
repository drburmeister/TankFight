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
//		Rectangle gun1 = new Rectangle();
//		gun1.setX(x + (body - length/4)/2);
//		gun1.setY( y + (body - length/4)/2);
//		gun1.setWidth(length);
//		gun1.setHeight(length/4);
//		gun1.setArcWidth(length/4);
//		gun1.setArcHeight(length/4);
//		gun1.setStroke(Color.BLACK);
//		gun1.setFill(Color.BLUE);
//		gun1.setRotate(th);
		
		if (visible)
		{
			double rotationCenterX = (x + length) / 2;
		    double rotationCenterY = (y + length/4) / 2;
			
			if (player == 1) {
				gc.setFill(Color.BLUE);
				gc.fillRoundRect(x, y, body, body, body/2, body/2);
		        
		        
			    gc.save();
			    gc.transform(new Affine(new Rotate(th, x + body/2, y + body/2)));
				gc.fillRoundRect(x + (body - length/4)/2, y + (body - length/4)/2, length, length/4, length/4, length/4);
				gc.setStroke(Color.BLACK);
				gc.strokeRoundRect(x + (body - length/4)/2, y + (body - length/4)/2, length, length/4, length/4, length/4);
				gc.restore();
				
				
			}
			else if (player == 2) { 
				gc.setFill(Color.RED);
				gc.fillRoundRect(x, y, body, body, 4, 4);
			}
		}
	}
}
