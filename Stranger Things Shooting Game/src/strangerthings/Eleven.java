package strangerthings;

import java.util.ArrayList;

import strangerthings.Game;
import javafx.scene.image.Image;


public class Eleven extends Sprite{

	private String name;
	private int lives;
	private boolean alive;
	private int points;
	private ArrayList<Attack> attack;

	//100 demogorgons to win
	public final static int WINNING_POINTS= 100;//100 DEMOGORGONS
	private final static Image ELEVEN_IMAGE = new Image("eleven1.png");
	private final static double INITIAL_X =500;
	private final static double INITIAL_Y = 100;
	public final static int ELEVEN_SPEED = 10;

	// Constructor for Eleven class, initializes Eleven object with default position and image
	public Eleven(String name) {
		super(Eleven.INITIAL_X, Eleven.INITIAL_Y, Eleven.ELEVEN_IMAGE);
		this.name = name;
		this.alive = true;
		this.attack = new ArrayList<Attack>();
		this.lives = 3; //3 lives at the beginning of the game
	}

	// Decreases Eleven's lives by 1
	void setLives(){
		this.lives--;
	}

	// Getter method for Eleven's remaining lives
	int getLives(){
		return this.lives;
	}

	// Getter method for Eleven's points as a String
	String getPoints(){
		String s = Integer.toString(this.points);
		return s;
	}

	// Getter method for Eleven's points as an integer
	int getPoints1(){
		return this.points;
	}

	// Getter method for Eleven's list of attacks
	ArrayList<Attack> getAttack(){
		return this.attack;
	}

	// Creates a new Attack object and adds it to Eleven's list of attacks
	void shoot() {
	    double bulletX = this.xPos + this.width; // Set the initial X position of the bullet to Eleven's right side
	    double bulletY = this.yPos + (this.height / 2); // Set the initial Y position of the bullet to Eleven's middle

	    this.attack.add(new Attack(bulletX, bulletY));	//reflects the attack image
	}

    // Sets Eleven's alive status to false
    void die(){
    	this.alive = false;
    }

    // Increases Eleven's points by the specified amount
    void gainPoints(int increase){
    	this.points+=increase;
    }

    // Checks if Eleven is alive
    boolean isAlive(){
    	return this.alive;
    }

    // Moves Eleven based on the current speed and direction
    void move() {
    	// Ensure Eleven stays within the window boundaries
    	if(this.xPos+this.dx >= 0 &&  this.xPos+this.dx <= Game.WINDOW_WIDTH-this.width )
			this.xPos += this.dx;
    	if(this.yPos+this.dy >= 0 && this.yPos+this.dy <= Game.WINDOW_HEIGHT-this.height)
    		this.yPos += this.dy;
	}

    void collectedPotion(){
    	if(this.lives<3){
    		this.lives++;
    	}
    }



}
