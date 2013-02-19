package bossStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Timer;

public class Boss extends BeatPlayer{
	
	Timer endTimer;
	
	public Boss(BufferedImage[] images)
	{
		super(images,650,540);
		x1 = 550;
		x2 = 650;
		x3 = 750;
		y1 = 460;
		y2 = 540;
		y3 = 620;
		
		position = 10;
		
		row = 1;
		column = 4;
		
		
	}
	
	public double getX()
	{	
		switch(column)
		{
			case 3: return x1;
			case 4: return x2;
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
			case 0: if(row > 0) {row-=1; super.move(0,-80);} break;
			case 1: if(row < 2) {row+=1; super.move(0,80);}break;
			case 2: if(column > 3) {column-=1; super.move(-80,0);}break;
			default: if(column < 5) {column+=1; super.move(80,0);}
		}
		
		battle = game.getBattle();
		Bullet tempBullet = battle.getField(getPosition());
		if(tempBullet != null){
		int type = tempBullet.getType();
		int damage = tempBullet.getDamage();
		
			if(type < 5){
				damage(damage);
				battle.clearField(getPosition()-1);
			}
		}
	}

	public void attack(int type,int damage)
	{
		if(bullet == null || (bullet != null && !bullet.isAlive()))
		{
				if(type == 1)
					bullet = new BulletPattern(getRow(),getColumn(),damage,weapon+5,0);
				else
					bullet = new BulletPattern(getRow(),getColumn(),damage,weapon+5,0);
				
				bullet.setGame(game);
				bullet.start();
				clearCombo();

		}
	}
	
	public void damage(int damage)
	{		
		health -= damage;
		if(health <= 0) 
		{
			getAnimationTimer().setDelay(300);
			setAnimationFrame(1,7);
			setAnimate(true);
			setActive(false);
			
			endTimer = new Timer(3000);
		}
	}
	
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		if(endTimer != null && endTimer.action(elapsedTime))
		{
			endTimer.setActive(false);
			game.setGameStatus(1);
		}
	}
		
}
