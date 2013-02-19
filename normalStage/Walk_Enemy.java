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
public class Walk_Enemy extends Enemys{
	
	BufferedImage[] img;
	Panda owner;
	public Walk_Enemy(Panda game,int x,int y,int t){
		super(x,y);
		owner = game;
		level = t;
		type = 1;
		img = game.getImages("resources/level"+level+"/"+level+"dm_walking.png",8,2);
		switch(level){
		case 1:	health = 20;
			   	score = 25;
			   	speed = 0.3;
			   	break;
		case 2:	health = 20;
	   			score = 25;
	   			speed = 0.3;
	   			break;
		case 3:	health = 30;
	   			score = 50;
	   			speed = 0.3;
	   			break;
		case 4:	health = 30;
				score = 50;
				speed = 0.3;
	   			break;
		case 5:	health = 40;
	   			score = 100;
	   			speed = 0.3;
	   			break;
		case 6:	health = 40;
	   			score = 100;
	   			speed = 0.3;
	   			break;
		default:health = 40;
				score = 100;
				speed = 0.3;
				break;
		}
		
		super.setImages(img);
		this.setVerticalSpeed(1);
		this.moveLeft(speed);
		
	
	}

	protected void animationChanged(int oldStat, int oldDir,int status, int direction) {
		switch (direction) {
			case LEFT:  setAnimationFrame(0, 7); break;
			case RIGHT: setAnimationFrame(8, 15); break;
		}
		switch (status) {
			case STAND:	setLoopAnim(false); break;
			case WALKING: setAnimate(true); setLoopAnim(true); break;
		}
	}
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
	}

}
