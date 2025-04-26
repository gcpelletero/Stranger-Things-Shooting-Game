package strangerthings;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;


//handles game updates and animations
public class GameTimer extends AnimationTimer{
	private ArrayList<Collectible> collectibles;

	private long startShoot;
	private Eleven eleven;
	private Scene scene;
	private GraphicsContext gc;

	private ArrayList<Demogorgon> demogorgons;
	private static boolean goUp;
	private static boolean goDown;
	private static boolean goLeft;
	private static boolean goRight;

	private double backgroundY;
	private Image background = new Image( "background.jpg" );
	private Image gameBackground = new Image("gamebg.png");
	private Image heart_full = new Image("heart_full.png");
	private Image heart_blank = new Image("heart_blank.png");
	private Image WILL = new Image("WILL.png");



	private final double X = 10;
	private final double Y = 10;
	private long startSpawn;
	public final static double SHOOT_DELAY = 0.08;
	public final static double SPAWN_DELAY = 1.5;

	public final static int WIDTH_PER_DEMOGORGON = 10;
	public final static int DEMOGORGON_INITIAL_XPOS = 200;
	public final static int MIN_DEMOGORGONS = 1;
	public final static int MAX_DEMOGORGONS = 1;
	private static boolean isShooting = false;

	// Constructor initializes the GameTimer with the game scene and graphics context
	public GameTimer(Scene gameScene, GraphicsContext gc) {
			this.gc = gc;
			this.scene = gameScene;
			this.eleven = new Eleven("Eleven");
			this.demogorgons = new ArrayList<Demogorgon>();
//			this.monsters = new ArrayList<Monster>();
			this.collectibles = new ArrayList<Collectible>();
//	    	this.startSpawn = this.startShoot = System.nanoTime();
	    	this.prepareActionHandlers();
	}

	// Overridden handle method is called in each frame to update the game state
	@Override
	public void handle(long now) {
		this.redrawBackgroundImage();

		this.autoShootSpawn(now);
		this.renderSprites();
		this.moveSprites();

		if(!this.eleven.isAlive()){

        	this.stop();// stops this AnimationTimer (handle will no longer be called) so all animations will stop
			this.gameOver();// draw Game Over text
		}
		if(this.eleven.getPoints1()== Eleven.WINNING_POINTS){
			this.stop();
			this.gameOver_WON();
		}

	}

	// Displays the "You Won" message when the player reaches the winning points
	private void gameOver_WON() {
		// TODO Auto-generated method stub
		this.gc.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 70));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("YOU WON!", 100, Game.WINDOW_HEIGHT/4);
		//GIF OF WILL BYERS BEING RELEASED

		this.gc.drawImage(WILL, 100, Game.WINDOW_HEIGHT/3);

	}

	// Displays the "Game Over" message when the player loses
	private void gameOver() {
		this.gc.drawImage(heart_blank, this.X, this.Y);
		this.gc.drawImage(heart_blank, this.X+50, this.Y);
		this.gc.drawImage(heart_blank, this.X+100, this.Y);

		this.gc.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 70));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("GAME OVER!", 100, Game.WINDOW_HEIGHT/4);
	}

	// Renders all sprites on the canvas
	void renderSprites() {
		//show el's life
		this.gc.drawImage(heart_full, this.X, this.Y);
		this.gc.drawImage(heart_full, this.X+50, this.Y);
		this.gc.drawImage(heart_full, this.X+100, this.Y);


		//shows eleven
		this.eleven.render(this.gc);

		//draws Sprites in ArrayLists
		for(Demogorgon demogorgon: this.demogorgons)
			demogorgon.render(this.gc);

		for(Attack a : this.eleven.getAttack())
			a.render(this.gc);

		for(Collectible c: this.collectibles)
			c.render(this.gc);

	}

	// Moves all sprites in the game
	void moveSprites() {
			this.moveEleven();
			this.moveDemogorgons();
			this.moveTelekinesis();
			this.moveCollectibles();
		}

	private void moveCollectibles(){
		for(int i=0; i<this.collectibles.size(); i++){
			Collectible a = this.collectibles.get(i);
			if(a.isVisible()){
				a.move();
				a.checkCollision(this.eleven);
			}
			else
				collectibles.remove(i);
		}
	}

	// Moves the telekinesis attacks
	private void moveTelekinesis() {
		ArrayList<Attack> a = this.eleven.getAttack();
		for(int i=0; i<a.size(); i++){
			Attack a1 = a.get(i);
			if(a1.isVisible())
				a1.move();
			else
				a.remove(i);
		}

	}

	// Moves the demogorgons and handles collisions with Eleven
	private void moveDemogorgons() {
		if(this.eleven.getLives()==2){
			this.gc.drawImage(heart_blank, this.X+100, this.Y);
		} else if(this.eleven.getLives()==1){
			this.gc.drawImage(heart_blank, this.X+100, this.Y);
			this.gc.drawImage(heart_blank, this.X+50, this.Y);
		}
		for(int i=0; i<this.demogorgons.size(); i++){
			Demogorgon d = this.demogorgons.get(i);
			if(d.isVisible()){
				d.move();
				d.demogorgonHitsEl(this.eleven);

			}
			else
				this.demogorgons.remove(i);
		}

	}

	// Redraws the background image on the canvas
	void redrawBackgroundImage() {
		//clear the canvas
        this.gc.clearRect(0, 0, Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT);

        this.gc.drawImage( gameBackground, 0, this.gameBackground.getHeight() );
        this.gc.drawImage( gameBackground, 0, this.backgroundY );


        if(this.backgroundY>=Game.WINDOW_HEIGHT)
        	this.backgroundY = Game.WINDOW_HEIGHT-this.background.getHeight();
    }

	// Prepares keyboard input event handlers
	private void prepareActionHandlers() {
	    this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent e) {
	            String code = e.getCode().toString();
	            if (code.equals("LEFT")) {
	                GameTimer.goLeft = true;
	            } else if (code.equals("RIGHT")) {
	                GameTimer.goRight = true;
	            } else if (code.equals("UP")) {
	                GameTimer.goUp = true;
	            } else if (code.equals("DOWN")) {
	                GameTimer.goDown = true;
	            } else if (code.equals("SPACE")) {
	                isShooting = true;
	            }
	        }
	    });

	    //key release for shooting
	    this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent e) {
	            String code = e.getCode().toString();
	            if (code.equals("LEFT")) {
	                GameTimer.goLeft = false;
	            } else if (code.equals("RIGHT")) {
	                GameTimer.goRight = false;
	            } else if (code.equals("UP")) {
	                GameTimer.goUp = false;
	            } else if (code.equals("DOWN")) {
	                GameTimer.goDown = false;
	            } else if (code.equals("SPACE")) {
	                isShooting = false;
	            }
	        }
	    });
	}

	// Moves Eleven based on keyboard input
	private void moveEleven() {
		this.gc.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("POINTS:",  650, 20);
		this.gc.fillText(eleven.getPoints(),  750, 20);
		if (GameTimer.goLeft)
            this.eleven.setDX(-Eleven.ELEVEN_SPEED);
		else if (GameTimer.goRight)
        	this.eleven.setDX(Eleven.ELEVEN_SPEED);
        else if(GameTimer.goUp)
        	this.eleven.setDY(-Eleven.ELEVEN_SPEED);
        else if(GameTimer.goDown)
        	this.eleven.setDY(Eleven.ELEVEN_SPEED);
        else{
        	this.eleven.setDX(0);
			this.eleven.setDY(0);
        }


        this.eleven.move();

	}

	// Controls the automatic shooting and spawning of monsters
	void autoShootSpawn(long currentNanoTime) {
	    double spawnElapsedTime = (currentNanoTime - this.startSpawn) / 1000000000.0;
	    double shootElapsedTime = (currentNanoTime - this.startShoot) / 1000000000.0;

	    //shoot
	    if (isShooting && shootElapsedTime > GameTimer.SHOOT_DELAY) {
	        this.eleven.shoot();
	        this.startShoot = System.nanoTime();
	    }

	    //spawn monsters
	    if (spawnElapsedTime > GameTimer.SPAWN_DELAY) {
	        // Uncomment the following line if you want to spawn demogorgons
	         this.spawnDemogorgons();
	        this.startSpawn = System.nanoTime();
	    }
	}

	// Spawns a random number of demogorgons with random positions
	private void spawnDemogorgons(){
	    Random r = new Random(); //random object to random random numbers

	    int demogorgonCount = r.nextInt(GameTimer.MAX_DEMOGORGONS - GameTimer.MIN_DEMOGORGONS + 1) + GameTimer.MIN_DEMOGORGONS; //random number of demogorgons within range
	    for (int i = 0; i < demogorgonCount; i++) {
	        int xPos = (int) (-Demogorgon.DEMOGORGON_IMAGE.getWidth()); //initial X position outside the left boundary
	        int yPos = r.nextInt((int) (Game.WINDOW_HEIGHT - Demogorgon.DEMOGORGON_IMAGE.getHeight())); //generates random Y position within game window height
	        Demogorgon demogorgon = new Demogorgon(xPos, yPos, collectibles);
	        this.demogorgons.add(demogorgon);


	    }

	}


}
