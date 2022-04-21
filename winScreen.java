import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class winScreen extends tankFight {

	public static void win(int loser) {
		if (loser == 1)	winner = 2;
		else winner = 1; 
	}
	
	public static void GameOver(GraphicsContext gc) {
		
		gc.setFill(Color.TAN);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setFont(Font.font("Times New Roman", 40));
		gc.setFill(Color.BLACK);
		gc.fillText("PLAYER " + winner + " WINS", 140, HEIGHT/2 -  20);
		gc.setFont(Font.font("Times New Roman", 20));
		gc.fillText("Press [ENTER] to play again", 170, HEIGHT/2);
	}
}
