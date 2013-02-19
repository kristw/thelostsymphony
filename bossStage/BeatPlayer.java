package bossStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class BeatPlayer extends AdvanceSprite {

	protected int position = 5;
	protected int health;
	protected int combo;
	protected int maxHealth;
	protected int row,column;
	protected int weapon;
	
	double x1,x2,x3;
	double y1,y2,y3;
	
	protected BulletPattern bullet;
	protected TheGame game;
	protected BattleField battle;	
	
	public BeatPlayer(BufferedImage[] images,double x, double y)
	{
		super(images,x,y);
	}
	
	public double getX()
	{
		switch(position)
		{
		case 1: return x1;
		case 2: return x2;
		case 3: return x3;
		case 4: return x1;
		case 5: return x2;
		case 6: return x3;
		case 7: return x1;
		case 8: return x2;
		default: return x3;
		}
	}
	
	public double getY()
	{
		switch(position)
		{
		case 1: return y1;
		case 2: return y1;
		case 3: return y1;
		case 4: return y2;
		case 5: return y2;
		case 6: return y2;
		case 7: return y3;
		case 8: return y3;
		default: return y3;
		}
	}
	
	public int getPosition()
	{
		return row*6 + column;
	}
	
	public void addCombo()
	{
		combo++;
	}
	
	public void clearCombo()
	{
		combo = 0;
	}
	
	public int getCombo() {
		return combo;
	}
	
	public int getHealth()
	{
		return health;
	}

	public int getMaxHealth() {
		
		return maxHealth;
	}
	
	public double getFactor()
	{
		double temp = (double)health/(double)maxHealth;
		return temp > 1.0 ? 1.0 : temp;
	}
	
	public double getComboFactor()
	{	
		double temp = (double)combo/100.0;
		return temp > 1.0 ? 1.0 : temp ;
	}
	
	public void setGame(TheGame game)
	{
		this.game = game;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}
	
}
