import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.media.AudioClip;

/*Tank Fight Game
 * Authors: Dennis Burmeister, Chris Piszczek, Andrew Leinauer 
 * Date: 4.20.22 
 */
public class tankFight extends Application {
	final String appName = "Tank Fight";
	final int FPS = 30; // frames per second
	final static int WIDTH = 600;
	final static int HEIGHT = 500;
	final static int COUNTER = 90;
	
	static int stopWatch = COUNTER;
	static int timer = 0;
	public static int cont = 0;
	static int winner = 0;
	static boolean pause = false;
	static boolean delay = false;
	static boolean tutorial = true;
	static boolean end = false;
	/**
	 * Set up initial data structures/values
	 */	
	static Sprite[] tanks = new Sprite[2];
	static Tank p1;
	static Tank p2;
	
	static Sprite[] bullets = new Sprite[6];
	static Sprite[] midWall = new Sprite[18];
	
	static Sprite[] player_walls = new Sprite[10];
	
	static bullet b0, b1, b2, b3, b4, b5;
	static wall w0, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17; 
	static wall pw0, pw1, pw2, pw3, pw4, pw5, pw6, pw7, pw8, pw9;
	
	//audio clips
	public static AudioClip bullHit;
	public static AudioClip bullWall;
	public static AudioClip kill;
	public static AudioClip placeBlock;
	
	static void initialize() //Method to initialize all objects
	{
		
		//load in audio files (wav)
		bullHit = new AudioClip(ClassLoader.getSystemResource("bullets.wav").toString());
		bullWall = new AudioClip(ClassLoader.getSystemResource("bulletWall.wav").toString());
		kill = new AudioClip(ClassLoader.getSystemResource("kill.wav").toString());
		placeBlock = new AudioClip(ClassLoader.getSystemResource("placeBlock.wav").toString());
		
		//create Sprites
		
		tanks[0] = p1 = new Tank();
		tanks[1] = p2 = new Tank();
		
		//3 bullets per player
		bullets[0] = b0 = new bullet();
		bullets[1] = b1 = new bullet();
		bullets[2] = b2 = new bullet();
		bullets[3] = b3 = new bullet();
		bullets[4] = b4 = new bullet();
		bullets[5] = b5 = new bullet();
	
		midWall[0] = w0 = new wall();
		midWall[1] = w1 = new wall();
		midWall[2] = w2 = new wall();
		midWall[3] = w3 = new wall();
		midWall[4] = w4 = new wall();
		midWall[5] = w5 = new wall();
		midWall[6] = w6 = new wall();
		midWall[7] = w7 = new wall();
		midWall[8] = w8 = new wall();
		midWall[9] = w9 = new wall();
		midWall[10] = w10 = new wall();
		midWall[11] = w11 = new wall();
		midWall[12] = w12 = new wall();
		midWall[13] = w13 = new wall();
		midWall[14] = w14 = new wall();
		midWall[15] = w15 = new wall();
		midWall[16] = w16 = new wall();
		midWall[17] = w17 = new wall();
		
		player_walls[0] = pw0 = new wall();
		player_walls[1] = pw1 = new wall();
		player_walls[2] = pw2 = new wall();
		player_walls[3] = pw3 = new wall();
		player_walls[4] = pw4 = new wall();
		player_walls[5] = pw5 = new wall();
		player_walls[6] = pw6 = new wall();
		player_walls[7] = pw7 = new wall();
		player_walls[8] = pw8 = new wall();
		player_walls[9] = pw9 = new wall();
		
		//reset timer
		timer = 0;
		//reset difficulty
		Sprite.v = 3;
		resetWatch();
		//play count down "animation"
		delay = true;
		
		//reset player positions
		p1.setPosition(WIDTH/4 - p1.body/2, HEIGHT/2 - p1.body/2);
		p2.setPosition(3*WIDTH/4 - p2.body/2, HEIGHT/2 - p2.body/2);
		p1.resume();
		p2.resume();
		
		for(int i = 0; i < midWall.length; i++) {
				midWall[i].setPosition(WIDTH/2 + wall.size/4, (wall.size)*i + wall.size/2);
				midWall[i].resume();
		}
	}
	
	/**
	 *  Update variables for one time step
	 */
	public static void update()
	{
		//make sure all sprites are updating
		p1.updateSprite(1);
		p2.updateSprite(2);
		
		b0.updateSprite(player_walls);
		b1.updateSprite(player_walls);
		b2.updateSprite(player_walls);
		b3.updateSprite(player_walls);
		b4.updateSprite(player_walls);
		b5.updateSprite(player_walls);
		
		//player and bullet collision
		for (int j = 0; j < tanks.length; j++) {		
			if (tanks[j].isActive()) { 
				for (int i = 0; i < bullets.length; i++) {
					if (bullets[i].isActive() && tanks[j].isHit(bullets[i])) {
						tanks[j].suspend();
						bullets[i].suspend();
						kill.play(); //play sound
						if (!tutorial) {
							winScreen.win(j + 1);
							end = true;
						}
					}
				}
			}
		}
		//bullet on bullet collision
		for (int x = 0; x < bullets.length - 1; x++) {
			if (bullets[x].isActive()) { 
				for (int y = x + 1; y < bullets.length; y++) {
					if(bullets[y].isActive()) {
						if (bullets[x].isCloserThan(bullets[y], bullet.rad*2)) {
							bullets[x].suspend();
							bullets[y].suspend();
							bullHit.play(); //play sound
						}
					}
				}
			}
		}
	}
	
	/**
	 *  Draw the game world
	 */
	void render(GraphicsContext gc) {

		
		//mid walls
		w0.render(gc, 0);
		w1.render(gc, 0);
		w2.render(gc, 0);
		w3.render(gc, 0);
		w4.render(gc, 0);
		w5.render(gc, 0);
		w6.render(gc, 0);
		w7.render(gc, 0);
		w8.render(gc, 0);
		w9.render(gc, 0);
		w10.render(gc, 0);
		w11.render(gc, 0);
		w12.render(gc, 0);
		w13.render(gc, 0);
		w14.render(gc, 0);
		w15.render(gc, 0);
		w16.render(gc, 0);
		w17.render(gc, 0);
		
		//players
		p1.render(gc, 1);
		p2.render(gc, 2);
		
		//bullets
		b0.render(gc, 1);
		b1.render(gc, 1);
		b2.render(gc, 1);
		b3.render(gc, 2);
		b4.render(gc, 2);
		b5.render(gc, 2);
		
		//player walls
		pw0.render(gc, 1);
		pw1.render(gc, 1);
		pw2.render(gc, 1);
		pw3.render(gc, 1);
		pw4.render(gc, 1);
		pw5.render(gc, 1);
		pw6.render(gc, 1);
		pw7.render(gc, 1);
		pw8.render(gc, 1);
		pw9.render(gc, 1);
		
	}

	//getter setter and resetter for stop watch used for count down "animation"
	public int getWatch() {
		return stopWatch;
	}
	
	public void countDown() {
		stopWatch--;
	}
	public static void resetWatch() {
		stopWatch = COUNTER;
	}
	/*
	 * Begin boiler-plate code...
	 * [Animation and events with initialization]
	 */
		
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage theStage) {
		theStage.setTitle(appName);

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Initial setup
		initialize();
		controls.setHandlers(theScene);
		
		// Setup and start animation loop (Timeline)
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					//draw screen/backdrop
					gc.setFill(Color.TAN);
					gc.fillRect(0, 0, WIDTH, HEIGHT);
					
					//play tutorial
					if(tutorial) {
						tutorialLevel.tutorial(gc);
					} //play count down animation
					else if (delay) {
						countDown();
						
						gc.setFont(Font.font("Times New Roman", 40));
						gc.setFill(Color.BLACK);
						gc.fillText("THE GAME WILL BEGIN IN", 40, 150);
						gc.setFont(Font.font("Times New Roman", 60));
						gc.fillText("" + (getWatch()/30 +  1), WIDTH/2 - 30, HEIGHT/2);
						
						if(getWatch() <=  0) {
							delay = false;
							resetWatch();
						}
					}
					//play end game screen 
					else if (end) {
						winScreen.GameOver(gc);
					}
					//play game
					else {
						if(!pause) {
							update();
							// draw frame
							render(gc);
							
							//increasing difficulty as time goes on
							timer++;							
							if (timer % 500 == 0) Sprite.v++;
							
							//Displaying current difficulty
							gc.setFont(Font.font("Times New Roman", 14));
							gc.setFill(Color.BLACK);
							gc.fillText("Difficulty/Speed Level = " + (Sprite.v - 2), 3, 14);
							}
						else { //pause menu
							pauseMenu.pause(gc);
						}
					}
				}
			);
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();

		theStage.show();
	}
	/*
	 * ... End boiler-plate code
	 */

}