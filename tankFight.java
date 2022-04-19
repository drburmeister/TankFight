import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/*Tank Fight Game
 * Authors: Dennis Burmeister, Chris Piszczek, Andrew Leinauer 
 * Date: 3.30.22 
 */
public class tankFight extends Application {
	final String appName = "Tank Wars";
	final int FPS = 30; // frames per second
	final static int WIDTH = 600;
	final static int HEIGHT = 500;
	
	/**
	 * Set up initial data structures/values
	 */	
	Sprite[] tanks = new Sprite[2];
	Tank p1;
	Tank p2;
	Sprite[] bullets = new Sprite[6];
	Sprite[] walls = new Sprite[20];
	
	bullet b0, b1, b2, b3, b4, b5;
	wall w0, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17, w18, w19;
	void initialize() //Method to initialize all objects
	{
		tanks[0] = p1 = new Tank();
		tanks[1] = p2 = new Tank();
		
		//3 bullets per player
		bullets[0] = b0 = new bullet();
		bullets[1] = b1 = new bullet();
		bullets[2] = b2 = new bullet();
		bullets[3] = b3 = new bullet();
		bullets[4] = b4 = new bullet();
		bullets[5] = b5 = new bullet();
	
		walls[0] = w0 = new wall();
		walls[1] = w1 = new wall();
		walls[2] = w2 = new wall();
		walls[3] = w3 = new wall();
		walls[4] = w4 = new wall();
		walls[5] = w5 = new wall();
		walls[6] = w6 = new wall();
		walls[7] = w7 = new wall();
		walls[8] = w8 = new wall();
		walls[9] = w9 = new wall();
		walls[10] = w10 = new wall();
		walls[11] = w11 = new wall();
		walls[12] = w12 = new wall();
		walls[13] = w13 = new wall();
		walls[14] = w14 = new wall();
		walls[15] = w15 = new wall();
		walls[16] = w16 = new wall();
		walls[17] = w17 = new wall();
		walls[18] = w18 = new wall();
		walls[19] = w19 = new wall();
		
		

		p1.setPosition(WIDTH/4 - p1.body/2, HEIGHT/2 - p1.body/2);
		p2.setPosition(3*WIDTH/4 - p2.body/2, HEIGHT/2 - p2.body/2);
		p1.resume();
		p2.resume();
		
		for(int i = 0; i < walls.length -1; i++) {

			walls[i].setPosition(WIDTH/2 + wall.size/2, (wall.size)*i + wall.size/2);
			walls[i].resume();
		}
	}
	
	void setHandlers(Scene scene)
	{
		//Handlers for when keys are pressed
		scene.setOnKeyPressed(
			e -> { 
					//PLAYER 1 INPUTS
					if (e.getCode() == KeyCode.TAB) { //If fire
						if(p1.active) {
							for (int i = 0; i < bullets.length/2; i++) {
								if (!bullets[i].active) { //If player has 3 bullets ready
									bullets[i].resume();  //activate the bullet
									bullets[i].hth = p1.th;  //save gunner angle
									bullets[i].vx = (bullets[i].v*Math.cos(Math.toRadians(bullets[i].hth)));
									bullets[i].vy = (bullets[i].v*Math.sin(Math.toRadians(bullets[i].hth)));
									bullets[i].setPosition(p1.x + (p1.length*Math.cos(Math.toRadians(bullets[i].hth)) + p1.body/2) , p1.y + (p1.length*Math.sin(Math.toRadians(bullets[i].hth)) + p1.body/2));
									//Initialize bullet start point to the end of the barrel	
									break;
								}
							}
						}
					} //Tank movement keys
					if (e.getCode() == KeyCode.W) p1.up = true;
					if (e.getCode() == KeyCode.A) p1.left = true;
					if (e.getCode() == KeyCode.S) p1.down = true;
					if (e.getCode() == KeyCode.D) p1.right = true;
					
					if (e.getCode() == KeyCode.Q) p1.Cclock = true;
					if (e.getCode() == KeyCode.E) p1.clock = true;
					
					if(e.getCode() == KeyCode.M) p1.resume();
					
					
					
					// PLAYER 2 INPUTS
					if (e.getCode() == KeyCode.P) { //If fire
						if(p2.active) {
							for (int i = 3; i < bullets.length; i++) {
								if (!bullets[i].active) { //If player has 3 bullets ready
									bullets[i].resume();  //activate the bullet
									bullets[i].hth = p2.th + 180;  //save gunner angle
									bullets[i].vx = (bullets[i].v*Math.cos(Math.toRadians(bullets[i].hth)));
									bullets[i].vy = (bullets[i].v*Math.sin(Math.toRadians(bullets[i].hth)));
									bullets[i].setPosition(p2.x + (p2.length*Math.cos(Math.toRadians(bullets[i].hth)) + p2.body/2) , p2.y + (p2.length*Math.sin(Math.toRadians(bullets[i].hth)) + p2.body/2));
									//Initialize bullet start point to the end of the barrel	
									break;
								}
							}
						}
					} //Tank movement keys
					if (e.getCode() == KeyCode.I) p2.up = true;
					if (e.getCode() == KeyCode.J) p2.left = true;
					if (e.getCode() == KeyCode.K) p2.down = true;
					if (e.getCode() == KeyCode.L) p2.right = true;
					
					if (e.getCode() == KeyCode.U) p2.Cclock = true;
					if (e.getCode() == KeyCode.O) p2.clock = true;
					
					if(e.getCode() == KeyCode.M) p2.resume();
				}
		);
		
		scene.setOnKeyReleased(
				e -> {	//When keys are released player/barrel should stop
					//PLAYER 1 RELEASE CODE
					if (e.getCode() == KeyCode.W) p1.up = false;
					if (e.getCode() == KeyCode.A) p1.left = false;
					if (e.getCode() == KeyCode.S) p1.down = false;
					if (e.getCode() == KeyCode.D) p1.right = false;
					
					if (e.getCode() == KeyCode.Q) p1.Cclock = false;
					if (e.getCode() == KeyCode.E) p1.clock = false; 
					
					//PLAYER 2 RELEASE CODE
					if (e.getCode() == KeyCode.I) p2.up = false;
					if (e.getCode() == KeyCode.J) p2.left = false;
					if (e.getCode() == KeyCode.K) p2.down = false;
					if (e.getCode() == KeyCode.L) p2.right = false;
				
					if (e.getCode() == KeyCode.U) p2.Cclock = false;
					if (e.getCode() == KeyCode.O) p2.clock = false; 
					}
			);
	}

	/**
	 *  Update variables for one time step
	 */
	public void update()
	{
		//make sure all sprites are updating
		p1.updateSprite();
		p2.updateSprite();
		
		b0.updateSprite();
		b1.updateSprite();
		b2.updateSprite();
		b3.updateSprite();
		b4.updateSprite();
		b5.updateSprite();
		
		//player and bullet collision
		for (int j = 0; j < tanks.length; j++) {		
			if (tanks[j].isActive()) { 
				for (int i = 0; i < bullets.length; i++) {
					if (bullets[i].isActive() && tanks[j].isHit(bullets[i])) {
						tanks[j].suspend();
						bullets[i].suspend();
					}
				}
			}
		}
		//bullet on bullet collision
		for (int x = 0; x < bullets.length - 1; x++) {
			if (bullets[x].isActive()) { 
				for (int y = x + 1; y < bullets.length; y++) {
					if(bullets[y].isActive()) {
						if (bullets[x].isCloserThan(bullets[y], bullet.rad + bullet.rad/4)) {
							bullets[x].suspend();
							bullets[y].suspend();
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
		// fill background and set play area

		//set playing field and render in all objectsW
		
		gc.setFill(Color.TAN);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		
		w0.render(gc);
		w1.render(gc);
		w2.render(gc);
		w3.render(gc);
		w4.render(gc);
		w5.render(gc);
		w6.render(gc);
		w7.render(gc);
		w8.render(gc);
		w9.render(gc);
		w10.render(gc);
		w11.render(gc);
		w12.render(gc);
		w13.render(gc);
		w14.render(gc);
		w15.render(gc);
		w16.render(gc);
		w17.render(gc);
		w18.render(gc);
		w19.render(gc);
		
		p1.render(gc, 1);
		p2.render(gc, 2);
		b0.render(gc);
		b1.render(gc);
		b2.render(gc);
		b3.render(gc);
		b4.render(gc);
		b5.render(gc);
		
	
		
		
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
		setHandlers(theScene);
		
		// Setup and start animation loop (Timeline)
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					update();
					// draw frame
					render(gc);
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