/**
 *
 */
package strangerthings;

import javafx.scene.image.Image;

public class Attack extends Sprite{
//	private int type;
	private int damage;

	// Constants for attack properties
	private final static double ATTACK_SPEED = 10;
	private final static int ORDINARY_BULLET_DAMAGE = 2;
	private final static Image ORDINARY_ATTACK_IMAGE = new Image("manacrystal_blank.png");
//	private final static Image UPGRADED_BULLET_IMAGE = new Image("images/fire.png");
//	private final static int UPGRADED_BULLET_DAMAGE = 25;
	public final static int ORDINARY_ATTACK = 0;
//	public final static int UPGRADED_BULLET = 1;

	/**
	 * Constructor for the Attack class. Initializes an Attack object with a given x and y position,
	 * using the image for ordinary attacks and setting the damage to the constant for ordinary bullet damage.
	 * @param x The x-coordinate of the Attack object.
	 * @param y The y-coordinate of the Attack object.
	 */
	Attack(double x, double y){
		super(x,y,Attack.ORDINARY_ATTACK_IMAGE);
//		this.type = type; //differentiates between ordinary & upgraded attack, affects the damage
		this.damage = Attack.ORDINARY_BULLET_DAMAGE; //different yung lvl of damage dito ng ordinary & upgraded
	}

    /**
     * Gets the damage value of the Attack object.
     * @return The damage value of the Attack object.
     */
    int getDamage(){
    	return this.damage;
    }

	/**
	 * Moves the Attack object horizontally to the left based on the ATTACK_SPEED.
	 * If the Attack object goes beyond the left edge of the scene, it sets visible to false.
	 */
	void move(){
		this.xPos -= Attack.ATTACK_SPEED;
		if(this.xPos <= 0){				// if this bullet passes through the top of the scene, set visible to false
			this.vanish();
		}
	}
}
