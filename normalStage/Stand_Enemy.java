package normalStage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Timer;

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
public class Stand_Enemy extends Enemys{

	BufferedImage[] img;
	Panda owner;
	Timer timer;
	public Stand_Enemy(Panda game,int x,int y,int t){
		super(x,y);
		owner = game;
		level = t;
		type = 3;
		timer = new Timer(2500);
		img = game.getImages("resources/level"+level+"/"+level+"dm_Shooting.png",8,2);
		switch(level){
		case 1:	health = 30;
			   	score = 45;
			   	speed = 0.3;
			   	break;
		case 2:	health = 30;
	   			score = 45;
	   			speed = 0.3;
	   			break;
		case 3:	health = 30;
	   			score = 90;
	   			speed = 0.3;
	   			break;
		case 4:	health = 40;
				score = 90;
				speed = 0.3;
	   			break;
		case 5:	health = 40;
	   			score = 180;
	   			speed = 0.3;
	   			break;
		case 6:	health = 40;
	   			score = 180;
	   			speed = 0.3;
	   			break;
		default:health = 40;
				score = 180;
				speed = 0.3;
				break;
		}
		
		super.setImages(img);
		this.setVerticalSpeed(1);
		this.setAnimationTimer(new Timer(500));
		this.moveLeft(0);
		this.setFrame(4);
	}

	public void update(long elapsedTime) {
		if(owner.player.getX()<this.getX()) this.setFrame(12);
		else this.setFrame(4);
	    if (timer.action(elapsedTime)) {
	   		EnemyBullet eBullet = new EnemyBullet(this,owner,level);
	   		if(owner.player.getX()>this.getX()){
	   			this.setDirection(RIGHT);
	   			eBullet.setLocation(this.getX()-5, this.getY()+50);
		   		eBullet.setHorizontalSpeed(0.4);
	  		}
	   		else {
	  			this.setDirection(LEFT);
	  			eBullet.setLocation(this.getX()-5, this.getY()+50);
		   		eBullet.setHorizontalSpeed(-0.4);
	   		}   	
	  		owner.ENEMYBULLET.add(eBullet);
	       }
	   }
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
	}

}
