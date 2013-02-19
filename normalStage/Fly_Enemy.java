package normalStage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/*
 * Created on 26 Á.¤. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Nut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Fly_Enemy extends Enemys{
	
	BufferedImage[] img;
	Panda owner;
	public Fly_Enemy(Panda game,int x,int y,int t){
		super(x,y);
		owner = game;
		level = t;
		type = 2;
		img = game.getImages("resources/level"+level+"/"+level+"dm_flying.png",8,2);
		switch(level){
		case 1:	health = 30;
			   	score = 35;
			   	speed = 0.5;
			   	break;
		case 2:	health = 30;
	   			score = 35;
	   			speed = 0.5;
	   			break;
		case 3:	health = 30;
	   			score = 70;
	   			speed = 0.5;
	   			break;
		case 4:	health = 40;
	   			score = 70;
	   			speed = 0.5;
	   			break;
		case 5:	health = 40;
	   			score = 140;
	   			speed = 0.5;
	   			break;
		case 6:	health = 40;
	   			score = 140;
	   			speed = 0.5;
	   			break;
		default:health = 40;
				score = 140;
				speed = 0.5;
				break;
		}
		super.setImages(img);
		this.moveLeft(speed);
	}
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
	}

	/**
	 * @return Returns the speed.
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @param speed The speed to set.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
