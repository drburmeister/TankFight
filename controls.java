import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class controls extends tankFight {
	static void setHandlers(Scene scene)
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
					if (e.getCode() == KeyCode.W && cont != 2) p1.up = true;
					if (e.getCode() == KeyCode.A && cont != 2) p1.left = true;
					if (e.getCode() == KeyCode.S && cont != 2) p1.down = true;
					if (e.getCode() == KeyCode.D && cont != 2) p1.right = true;
					
					if (e.getCode() == KeyCode.Q && cont != 2) p1.Cclock = true;
					if (e.getCode() == KeyCode.E && cont != 2) p1.clock = true;
					
					if (e.getCode() == KeyCode.SHIFT) { //Block Placing
						for(int w = 0; w < player_walls.length/2; w++) {
							if(!player_walls[w].isActive()) {
								player_walls[w].setPosition(p1.x + 2*p1.body, p1.y);
								int c =  0;
								while (c < player_walls.length/2)  {
									if(player_walls[w].wallOverlap(player_walls[c]) && c != w) {
										player_walls[w].setPosition(player_walls[c].x,  player_walls[c].y + wall.size + 1);
										c = 0;
									}
									else {
										c++;
									}
								}
								player_walls[w].resume(); 
								tankFight.placeBlock.play();
								break;
							}
						}
					}
					
					if (e.getCode() == KeyCode.R && cont == 3) {
						p1.setPosition(WIDTH/4 - p1.body/2, HEIGHT/3 - p1.body/2);
						p1.resume();
					}
					
					
					
					
					// PLAYER 2 INPUTS
					if (e.getCode() == KeyCode.P && cont != 1) { //If fire
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
					if (e.getCode() == KeyCode.I && cont != 2) p2.up = true;
					if (e.getCode() == KeyCode.J && cont != 2) p2.left = true;
					if (e.getCode() == KeyCode.K && cont != 2) p2.down = true;
					if (e.getCode() == KeyCode.L && cont != 2) p2.right = true;
					
					if (e.getCode() == KeyCode.U && cont != 2) p2.Cclock = true;
					if (e.getCode() == KeyCode.O && cont != 2) p2.clock = true;
					
					if (e.getCode() == KeyCode.H) { //Block Placing
						for(int w = player_walls.length/2; w < player_walls.length; w++) {
							if(!player_walls[w].isActive()) {
								player_walls[w].setPosition(p2.x - 2*p2.body, p2.y);
								int c =  player_walls.length/2;
								while (c < player_walls.length)  {
									if(player_walls[w].wallOverlap(player_walls[c]) && c != w) {
										player_walls[w].setPosition(player_walls[c].x,  player_walls[c].y + wall.size + 1);
										c = 0;
									}
									else {
										c++;
									}
								}
								player_walls[w].resume(); 
								tankFight.placeBlock.play();
								break;
							}
						}
					}
					
					//Pause Key
					if(e.getCode() == KeyCode.ESCAPE) {
						if(pause) {
							pause = false;
							delay = true;
						}
						else { pause = true; }
					}
					//Tutorial Progression Key
					if (e.getCode() == KeyCode.SPACE) {
						cont++;
						p1.resume();
						p2.resume();
					}
					//Skip tutorial/reset game key
					if (e.getCode() == KeyCode.ENTER) {
						if(tutorial || end) {
							tutorial = false;
							initialize();
							end = false;
						}
					}
					
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
}
