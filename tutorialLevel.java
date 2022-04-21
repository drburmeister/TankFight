import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class tutorialLevel extends tankFight{
	
	public static void tutorial(GraphicsContext gc) {
		if(cont == 0) {
			p1.setPosition(WIDTH/4 - p1.body/2, HEIGHT/2 - p1.body/2);
			p2.setPosition(3*WIDTH/4 - p2.body/2, HEIGHT/2 - p2.body/2);
			
			gc.setFont(Font.font("Times New Roman", 30));
			gc.setFill(Color.BLACK);
			gc.fillText("Welcome to Tank Fight!", tankFight.WIDTH/4, tankFight.HEIGHT/8);
			gc.setFont(Font.font("Times New Roman", 20));
			gc.fillText("This will be a short tutorial on how to play and the rules of the game", tankFight.WIDTH/20, 83);
			gc.fillText("Press [SPACE] to continue and [ENTER] at any time to skip this tutorial", 4, 103);
		}
		else if(cont == 1) {
			gc.setFont(Font.font("Times New Roman", 20));
			gc.setFill(Color.BLACK);
			gc.fillText("Two players will play as the tanks below", tankFight.WIDTH/5, 83);
			gc.fillText("Move them around with [WASD] and [IJKL]", tankFight.WIDTH/5, 103);
			gc.setFont(Font.font("Times New Roman", 16));
			gc.fillText("To rotate the gunners use [Q] and [E] for player 1 and [U] and [O] for player 2", 40, 123);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("Press [SPACE] to continue or [ENTER] to Skip", 170, HEIGHT - 20);
			
			p1.resume();
			p2.resume();
			p1.updateSprite(1);
			p2.updateSprite(2);
			p1.render(gc, 1);
			p2.render(gc, 2);
		}
		else if (cont == 2) {
			gc.setFill(Color.BLACK);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("Press [SPACE] to continue or [ENTER] to Skip", 170, HEIGHT - 20);
			gc.setFont(Font.font("Times New Roman", 16));
			gc.fillText("To shoot press [TAB] and [P] Respectively", 40, 20);
			gc.setFont(Font.font("Times New Roman", 14));
			gc.fillText("Each player can have 3 bullets out at once and they will bounce 3 times before they disappear", 40, 40);
			gc.fillText("However if a bullet hits another bullet or player it will immediately disappear", 40, 60);
			gc.fillText("If you get hit by ANY bullet you will lose", 40, 80);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("Over time the difficulty/speed of everything in the game will slowly increase to mitigate longer fights", 40, 100);
			
			
			p1.setPosition(WIDTH/4 - p1.body/2, HEIGHT/3 - p1.body/2);
			p2.setPosition(WIDTH/4 - p2.body/2, 2*HEIGHT/3 - p2.body/2);
			p1.th = 0;
			p2.th = -180;
			update();
			p1.render(gc, 1);
			p2.render(gc, 2);
			//bullets
			b0.render(gc, 1);
			b1.render(gc, 1);
			b2.render(gc, 1);
			b3.render(gc, 2);
			b4.render(gc, 2);
			b5.render(gc, 2);

		}
		else if (cont == 3) {
			gc.setFill(Color.BLACK);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("Press [SPACE] to continue or [ENTER] to Skip", 170, HEIGHT - 20);
			gc.setFont(Font.font("Times New Roman", 16));
			gc.fillText("Lastly, lets talk about walls", 40, 20);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("There are two types of walls in this game, white walls and black walls", 40, 40);
			gc.fillText("White walls can be shot through but not driven through and they will mark the border between players", 40, 60);
			gc.fillText("Black walls can be driven through but CANNOT be shot through...", 40, 80);
			gc.fillText("These walls are placed by the players with [LSHIFT] and [H] with each player having a limit of 5 out at once...", 40, 100);
			gc.fillText("These walls will reflect bullets like the edges of the map but will be destroyed on impact", 40, 120);
			gc.setFont(Font.font("Times New Roman", 18));
			gc.fillText("Player 1 try placing some blocks to get a feel for how they work!", 40, HEIGHT - 140);
			gc.setFont(Font.font("Times New Roman", 12));
			gc.fillText("If you hit yourself press [R] to respawn (only works in this tutorial)", 40, HEIGHT - 120);
			gc.fillText("Remember [WASD] to move, [TAB] to shoot and [Q] or [E] to rotate", 40, HEIGHT - 100);
			
			
			update();
			p1.render(gc, 1);
			//bullets

			
			w5.render(gc, 0);
			w6.render(gc, 0);
			w7.render(gc, 0);
			w8.render(gc, 0);
			w9.render(gc, 0);
			w10.render(gc, 0);
			w11.render(gc, 0);
			
			//player walls
			pw0.render(gc, 1);
			pw1.render(gc, 1);
			pw2.render(gc, 1);
			pw3.render(gc, 1);
			pw4.render(gc, 1);
			
			b0.render(gc, 1);
			b1.render(gc, 1);
			b2.render(gc, 1);
			

		}
		
		else {
			tutorial = false;
			initialize();
		}
	}
}
