package strangerthings;



import javafx.scene.image.Image;

abstract class Collectible extends Sprite{
	double speed;
	private final static double COLLECTIBLE_SPEED = 4.5;

	Collectible(double x, double y, Image image){
		super(x,y,image);
		this.speed = Collectible.COLLECTIBLE_SPEED;
	}

	void move(){
		this.xPos += this.speed;
		if(this.xPos >= Game.WINDOW_WIDTH){
			this.vanish();
		}
	}

	abstract void checkCollision(Eleven eleven);

}