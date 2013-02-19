/*
 * Created on 17 ¡.¾. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package normalStage;

import com.golden.gamedev.object.sprite.AdvanceSprite;

/**
 * @author Donut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Enemys extends AdvanceSprite{
	public static final int STAND = 1, WALKING = 2, FLYING = 2, SHOOTING = 2;
	public static final int LEFT = 1, RIGHT = 2;
	int level;
	int health;
	int score;
	int type;
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}
	double speed;
	public Enemys(int x,int y){
		super(x,y);
		
	}

	

	/**
	 * @return Returns the health.
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * @param health The health to set.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return Returns the score.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(int score) {
		this.score = score;
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
	public void moveLeft(double x){
		 this.setHorizontalSpeed(-x);
		 this.setStatus(WALKING);
		 this.setDirection(LEFT);
	}
	public void moveRight(double x){
		 this.setHorizontalSpeed(x);
		 this.setStatus(WALKING);
		 this.setDirection(RIGHT);
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
