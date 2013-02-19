package normalStage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;

/*
 * Created on 3 Á.¤. 2549
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
public class Player extends AdvanceSprite{

	 /******************** CONTROLLER ARRAY CONSTANTS ***********************/
	public static final int LEFT_KEY = 0, RIGHT_KEY = 1,
							UP_KEY = 2, DOWN_KEY = 3,
							FIRE_KEY = 4, CTRL_KEY = 5;
	/*********************** STATE AND DIRECTION ***************************/
	
	public static final int STAND = 1, WALKING = 2;
	public static final int LEFT = 1, RIGHT = 2;
	
	boolean deadFlag;
	boolean jumpFlag;
	
	Timer	jumpTimer;
	Timer 	fireTimer;
	
	/****************************** IMAGES *********************************/
	
	BufferedImage img;
	BufferedImage[] deadImg;
	BufferedImage[]  bulletImage;
	/****************************** PROPERTIES **********************************/
	Panda owner;
	
	int health,mana,score;
	int cont;
	int maxHealth,maxMana;
	int[]	controls;

	/****************************************************************************/
	/**************************** CONSTRUCTOR ***********************************/
	/****************************************************************************/
	double posY;
	public Player(Panda owner, int[] controls,double x,double y,int type){

		super(owner.getImages("resources/panda/pd_walking.png", 8, 2), x, y); 
		this.owner = owner;
		this.controls = controls;
		this.maxHealth=((LostSymphony)owner.parent).getMaxHealth();		
		this.maxMana=((LostSymphony)owner.parent).getMaxMana();
		this.health = maxHealth;
		this.mana = maxMana;
		this.score = ((LostSymphony)owner.parent).getScore();
		this.deadFlag = false;
		
		//loading all images
		bulletImage		= owner.getImages("resources/bull.png", 2, 1);
			
		jumpTimer = new Timer(500);
		fireTimer = new Timer(200);
		
		resetState();
		posY =y;
	}
	
	public void resetState() {
		setAnimation(STAND, RIGHT);
		
		setSpeed(0, 0);
		deadFlag = false;
		jumpFlag = false;
		jumpTimer.setActive(false);
		fireTimer.setActive(false);
		//setVerticalSpeed(3);
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
	public void update(long elapsedTime) {
		//jumpFlag = false;
		if (fireTimer.isActive() && fireTimer.action(elapsedTime)) {
			// able to fire again
			fireTimer.setActive(false);
		}
		if (/*jumpTimer.isActive() &&*/ jumpTimer.action(elapsedTime)) {
			// incremental jump time has been used up
			jumpTimer.setActive(false);
			jumpFlag = false;
		}
	
//		 detect keyboard
		detectKeyboardEvent(elapsedTime);
		super.update(elapsedTime);
	}
	private void detectKeyboardEvent(long elapsedTime) {
		
		double maxSpeed = 0.35;
		if (owner.keyDown(controls[LEFT_KEY])) {
			// moving left
			// momentum speed :-)
			addHorizontalSpeed(elapsedTime, -0.002, -maxSpeed);
			setDirection(LEFT);
			if(getVerticalSpeed() == 0)	setStatus(WALKING);
		} else if (owner.keyDown(controls[RIGHT_KEY])) {
			// moving right
			addHorizontalSpeed(elapsedTime, 0.002, maxSpeed);
			setDirection(RIGHT);
			if(getVerticalSpeed() == 0)	setStatus(WALKING);
		} else { // left and right arrow not pressed
			// gradually stop robo horizontal speed
			if (getHorizontalSpeed() > 0) {
				addHorizontalSpeed(elapsedTime, -0.05, 0);
			} else if (getHorizontalSpeed() < 0) {
				addHorizontalSpeed(elapsedTime, 0.05, 0);
			} else { // getHorizontalSpeed() == 0
				// robo is not moving, set status as standing
				setStatus(STAND);
			}
		}
		
			if (owner.keyPressed(controls[UP_KEY])) {
				if (/*true*/!jumpFlag && getVerticalSpeed() == 0) {
				// jump robo jump!
				jumpFlag = true;
				setVerticalSpeed(-0.6);

				// activate incremental jump
				jumpTimer.setActive(true);
			}}
	
     		// gravity 
			if (!jumpFlag /*&& getVerticalSpeed() < 0 */&& !jumpTimer.isActive()) {
				setVerticalSpeed(0.45);
			}
			
			if (owner.keyPressed(controls[FIRE_KEY]) &&
					fireTimer.isActive() == false) {
					// refresh refire timer
					fireTimer.setActive(true);
					
					this.setStatus(WALKING);
					if(getDirection() == LEFT) this.setDirection(LEFT);
					else this.setDirection(RIGHT);
					
					// add projectile and fire animation
					BufferedImage image = (getDirection() == LEFT) ?
										  bulletImage[0] : bulletImage[1];
					//owner.playSound("resources/sound1.wav");
					owner.BULLET.add(new Bullet(this,owner));
					//owner.playfield.add(new FireAnimation(this));
				}
		
		}
	
	

	 /****************************************************************************/
	 /**************************** RENDER PLAYER *********************************/
	 /****************************************************************************/

	public void render(Graphics2D g, int x, int y) {
//		 when flying or jumping we use fixed frame
		if (jumpFlag) {
			setFrame(5);
		}
		super.render(g, x, y);
	}
	
	
	/****************************************************************************/
	 /**************************** BULLET SPRITE ********************************/
	 /****************************************************************************/

//	 projectile is vanish when out of screen.
	class Bullet extends Sprite {
		int damage;
		int mana;
		Panda owner;
		public Bullet(Player player,Panda game) {
			double bulletSpeed;
			owner = game;
			damage = 10;
			mana = 3;
			bulletSpeed = 0.45;
			bulletImage = owner.getImages("resources/panda/note.png", 2, 1);
					
			if(player.getMana()>= mana){
			player.setMana(player.getMana()- mana);
			owner.playSound("resources/sound1.wav");
			if (player.getDirection() == Player.LEFT) {
				setImage(player.bulletImage[0]);
				setLocation(player.getX()-5, player.getY()+14);
				setHorizontalSpeed(-bulletSpeed);

			} else {
				setImage(player.bulletImage[1]);
				setLocation(player.getX()+player.getWidth()+5,
							player.getY()+14);
				setHorizontalSpeed(bulletSpeed);
			}
			}	
		}
			

		public void update(long elapsedTime) {
			super.update(elapsedTime);

			// removed from playfield when
			// the projectile is 50 pixels out of screen view area
			if (!isOnScreen(50, 50, 50, 50)) {
				setActive(false);
			}
		}

	}
	
	public int getHealth(){
		return health;
	}
	public void setHealth(int x){
		if(x>=maxHealth)health = maxHealth;
		else health = x;
	}
	public int getScore(){
		return score;
	}
	public void addScore(int x){
		score+=x;
	}
	public int getMana(){
		return mana;
	}
	public void setMana(int x){
		if(x>=maxMana) mana = maxMana;
		else mana = x;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void setMaxHealth(int x){
		maxHealth = x;
	}
	public int getMaxMana(){
		return maxMana;
	}
	public void setMaxMana(int x){
		maxMana = x;
	}
	public void dead(){
		owner.playSound("resources/boom.wav");
		owner.music.requestStop();
		this.setActive(false);
	    BufferedImage[] images = owner.getImages("resources/panda/pd_dying.png", 8, 1);
	    AnimatedSprite death = new AnimatedSprite(images,this.getX()-19,this.getY()+20);
	    death.setAnimate(true);
	    death.setLoopAnim(true);	    
	    owner.playfield.add(death);
	 	deadFlag = true;
	}
	public boolean isDead(){
		return deadFlag;
	}

}
