import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class pauseMenu extends tankFight {
	public static void pause (GraphicsContext gc) {
		//Pause Menu Text
		gc.setFont(Font.font("Times New Roman", 16));
		gc.setFill(Color.BLACK);
		gc.fillText("THE GAME IS NOW PAUSED PRESS [ESC] AGAIN TO CONTINUE", tankFight.WIDTH/8, 20);

		//Player 1 Controls Text
		gc.setFont(Font.font("Times New Roman", 14));
		gc.fillText("Player 1 Controls:", 20, 60);
		gc.setFont(Font.font("Times New Roman", 10));
		gc.fillText("Movement: [W] = Up, [A] = Left, [S] = Down, [D] = Right", 20, 80);
		gc.fillText("Gunner: [Q] = Rotate Counter Clockwise, [E] = Clockwise", 20, 100);
		gc.fillText("Shooting and Placing: [TAB] = Fire, [LShift] = Place Wall", 20, 120);
		
		//Player 2 controls text
		gc.setFont(Font.font("Times New Roman", 14));
		gc.fillText("Player 2 Controls:", WIDTH - 250, 60);
		gc.setFont(Font.font("Times New Roman", 10));
		gc.fillText("Movement: [I] = Up, [J] = Left, [K] = Down, [L] = Right", WIDTH - 250, 80);
		gc.fillText("Gunner: [U] = Rotate Counter Clockwise, [O] = Clockwise", WIDTH - 250, 100);
		gc.fillText("Shooting and Placing: [P] = Fire, [H] = Place Wall", WIDTH - 250, 120);
	}
}
