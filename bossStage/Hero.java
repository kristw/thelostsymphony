package bossStage;

import java.awt.image.BufferedImage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class Hero extends BeatPlayer {
		
	protected BulletPattern bullet;
	protected BattleField battle;
	
	protected Timer attackTimer;
	private Timer endTimer;
	private int weaponLimit;
	
	public Hero(BufferedImage[] images)
	{
		super(images,300,550);
		x1 = 200;
		x2 = 300;
		x3 = 400;
		y1 = 670;
		y2 = 550;
		y3 = 630;
		position = 7;
		row = 1;
		column = 1;
		weapon = 0;
		
		setAnimationFrame(0,0);
		setAnimate(true);
	}
	
	public double getX()
	{
		switch(column)
		{
		case 0: return x1;
		case 1: return x2;
		default: return x3;
		}
	}
	
	public double getY()
	{
		switch(row)
		{
		case 0: return y1;
		case 1: return y2;
		default: return y3;
		}
	}
	
	public void move(int i)
	{
		switch(i)
		{
			case 0: if(row > 0) {row-=1; super.move(0,-80);}break;
			case 1: if(row < 2) {row+=1; super.move(0,80);}break;
			case 2: if(column > 0) {column-=1; super.move(-80,0);}break;
			default: if(column < 2) {column+=1; super.move(80,0);}
		}
		
		battle = game.getBattle();
		Bullet tempBullet = battle.getField(getPosition());
		if(tempBullet != null){
			int type = tempBullet.getType();
			int damage = tempBullet.getDamage();
			
			if(type > 4){
				damage(damage);
				battle.clearField(getPosition()-1);
			}
		}
		
	}
	
	public void attack()
	{		
		if(bullet == null || (bullet != null && !bullet.isAlive()))
		{
			attackTimer = new Timer(50);			
			getAnimationTimer().setDelay(50);
			setAnimationFrame(0,3);
			setAnimate(true);
		}
	}
	
	public void update(long elapsedTime)
	{
		if(attackTimer != null && attackTimer.action(elapsedTime))
		{
			bullet = new BulletPattern(getRow(),getColumn(),combo * 30,weapon,1);
			bullet.setGame(game);
			bullet.start();
			game.playSound("resources2/try.wav");
			clearCombo();
			attackTimer.setActive(false);
		}
		
		if(endTimer != null && endTimer.action(elapsedTime))
		{
			game.setGameStatus(2);
		}
		
		super.update(elapsedTime);
	}
	
	public void damage(int damage)
	{
		health -= damage;
		getAnimationTimer().setDelay(300);
		setAnimationFrame(4,8);
		setAnimate(true);
		
		if(health <= 0) 
		{
			setAnimationFrame(8,8);
			endTimer = new Timer(2000);
		}
		
	}
	
	public void setWeaponLimit(int level)
	{
		if(level < 3)
			weaponLimit = 0;
		else
			weaponLimit = (level - 2);
	}
	
	public void changeWeapon() {
			weapon++;
			if(weapon > weaponLimit)
				weapon = 0;
	}
	
}
