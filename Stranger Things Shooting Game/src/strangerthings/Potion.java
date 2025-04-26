package strangerthings;

import javafx.scene.image.Image;

class Potion extends Collectible{
	private final static Image POTION_IMAGE = new Image("potion.png");

	Potion(double x, double y) {
		super(x, y, Potion.POTION_IMAGE);
		// TODO Auto-generated constructor stub
	}
	void checkCollision(Eleven eleven){
		if(this.collidesWith(eleven)){
			this.vanish();
			eleven.collectedPotion();
		}
	}


}