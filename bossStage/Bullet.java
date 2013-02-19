package bossStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Bullet extends Sprite{
	
	private int damage;
	private int type;
	
	public Bullet(BufferedImage image,int damage,int type,int target)
	{
		setImage(image);
		this.type = type;
		this.damage = damage;
	}
	
	public int getType()
	{
		return this.type;
	}

	public int getDamage() {
		// TODO Auto-generated method stub
		return this.damage;
	}
}
