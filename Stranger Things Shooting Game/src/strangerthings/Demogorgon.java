package strangerthings;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;



public class Demogorgon extends Sprite{
	private int health;
	private int increase = 5;
	double DEMOGORGON_SPEED = 4.5;
	final static Image DEMOGORGON_IMAGE = new Image("demogorgon.png");

	// Constant for Demogorgon health
	private final static int DEMOGORGON_HEALTH = 10;
	private static final int ITEM_FREQUENCY = 30;
	private ArrayList<Collectible> collectibles;

	// Constructor for Demogorgon class, initializes Demogorgon object with given x, y, and Demogorgon image
	public Demogorgon(int x, int y,  ArrayList<Collectible> collectibles) {
		super(x, y, DEMOGORGON_IMAGE);
		this.health = DEMOGORGON_HEALTH;
		this.collectibles = collectibles;

		// TODO Auto-generated constructor stub
	}

	// Increases Demogorgon speed
	void setSpeed(){
		this.DEMOGORGON_SPEED++;
	}

	// Moves the Demogorgon horizontally to the right based on its speed
	void move(){
		this.xPos += this.DEMOGORGON_SPEED;
//		this.yPos += Math.sin(this.xPos / 50.0) * 5.0; //adds vertical variation
		if(this.xPos >= Game.WINDOW_WIDTH-100 ){	// if this monster passes through the bottom of the scene, set visible to false
			this.vanish();
        }
	}

	//the demogorgon dies once its health reaches 0
	void hit(int damage,Eleven eleven){
		this.health-=damage;
		if(this.health<=0){
			eleven.gainPoints(increase);
			this.die();
		}
	}

	// Sets the Demogorgon to be invisible
	private void die() {
		int a;
		Collectible newCollectible;
		Random r =new Random();

		a = r.nextInt(Demogorgon.ITEM_FREQUENCY);

		if(a%5 == 0){
			newCollectible = new Potion(this.xPos,this.yPos);
			this.collectibles.add(newCollectible);
		}

		this.vanish();
	}

	// Checks for collisions with Eleven and Eleven's attacks
	void demogorgonHitsEl(Eleven eleven){
		for	(int i = 0; i < eleven.getAttack().size(); i++)	{
			if (this.collidesWith(eleven.getAttack().get(i))){
				this.hit(eleven.getAttack().get(i).getDamage(), eleven);
				eleven.getAttack().get(i).vanish();
			}
		}
		if(this.collidesWith(eleven)){
			eleven.setLives();
			this.vanish();
			if(eleven.getLives()==0){
				eleven.die();
			}

		}
	}









}
