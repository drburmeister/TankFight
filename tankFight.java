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
	//Tank p2;
	Sprite[] bullets = new Sprite[6];
	
	bullet b0, b1, b2, b3, b4, b5;
	void initialize() //Method to initialize all objects
	{
		tanks[0] = p1 = new Tank();
		//	tanks[1] = p2 = new Tank();
		
		//3 bullets per player
		bullets[0] = b0 = new bullet();
		bullets[1] = b1 = new bullet();
		bullets[2] = b2 = new bullet();
		bullets[3] = b3 = new bullet();
		bullets[4] = b4 = new bullet();
		bullets[5] = b5 = new bullet();


		p1.setPosition(WIDTH/2 - p1.body/2, HEIGHT/2 - p1.body/2);
	//	p2.setPosition(3*WIDTH/4, HEIGHT/2);
		p1.resume();
	//	p2.resume();
	}
	
	void setHandlers(Scene scene)
	{
		//Handlers for when keys are pressed
		scene.setOnKeyPressed(
			e -> { 
					if (e.getCode() == KeyCode.SPACE) { //If fire
						if (!b0.active) { //If player has 3 bullets ready
							b0.resume();  //activate the bullet
							b0.hth = p1.th;  //save gunner angle
							b0.setPosition(p1.x + (p1.length*Math.cos(Math.toRadians(b0.hth)) + p1.body/2) , p1.y + (p1.length*Math.sin(Math.toRadians(b0.hth)) + p1.body/2));
							//Initialize bullet start point to the end of the barrel							
						}
						
						else if (!b1.active) { //if player has 2 bullets ready
							b1.resume();
							b1.hth = p1.th;
							b1.setPosition(p1.x + (p1.length*Math.cos(Math.toRadians(b1.hth)) + p1.body/2) , p1.y + (p1.length*Math.sin(Math.toRadians(b1.hth)) + p1.body/2));
							
						}
						else if (!b2.active) { //if player has 1 bullet remaining
							b2.resume();
							b2.hth = p1.th;
							b2.setPosition(p1.x + (p1.length*Math.cos(Math.toRadians(b2.hth)) + p1.body/2) , p1.y + (p1.length*Math.sin(Math.toRadians(b2.hth)) + p1.body/2));
							
						}
					} //Tank movement keys
					if (e.getCode() == KeyCode.W) p1.up = true;
					if (e.getCode() == KeyCode.A) p1.left = true;
					if (e.getCode() == KeyCode.S) p1.down = true;
					if (e.getCode() == KeyCode.D) p1.right = true;
					
					if (e.getCode() == KeyCode.Q) p1.Cclock = true;
					if (e.getCode() == KeyCode.E) p1.clock = true;
				}
		);
		
		scene.setOnKeyReleased(
				e -> {	//When keys are released player/barrel should stop
					if (e.getCode() == KeyCode.W) p1.up = false;
					if (e.getCode() == KeyCode.A) p1.left = false;
					if (e.getCode() == KeyCode.S) p1.down = false;
					if (e.getCode() == KeyCode.D) p1.right = false;
					
					if (e.getCode() == KeyCode.Q) p1.Cclock = false;
					if (e.getCode() == KeyCode.E) p1.clock = false; 
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
		b0.updateSprite();
		b1.updateSprite();
		b2.updateSprite();
		b3.updateSprite();
		b4.updateSprite();
		b5.updateSprite();
		
	//	p2.updateSprite();
	}
	
	/**
	 *  Draw the game world
	 */
	void render(GraphicsContext gc) {
		// fill background and set play area

		//set playing field and render in all objectsW
		gc.setFill(Color.TAN);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		p1.render(gc, 1);
		b0.render(gc);
		b1.render(gc);
		b2.render(gc);
		b3.render(gc);
		b4.render(gc);
		b5.render(gc);
	//	p2.render(gc, 2);

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