package bossStage;

public class BattleField {
	Bullet[] field;
	private TheGame game;
	private Hero hero;
	private Boss boss;
	
	public void setGame(TheGame game) {
		this.game = game;
		hero = game.getHero();
		boss = game.getBoss();
	}

	public BattleField()
	{
		field = new Bullet[18];
	}
	
//	public int getField(int i)
//	{
//		return field[i];
//	}
	
	public Bullet getField(int i)
	{
		return field[i];
	}
	
	public void setField(int i,Bullet bullet)
	{
		
		field[i] = bullet;

		if(bullet.getType() < 5)
		{
			if(boss.getPosition() == i){
				boss.damage(bullet.getDamage());
				field[i] = null;
			}
		}
		else
		{
			if(hero.getPosition() == i){
				System.out.println("hit");
					hero.damage(bullet.getDamage());
					field[i] = null;
			}
		}
	}
	
	public void clearField(int i)
	{
		//field[i] = 0;
		field[i] = null;
	}

	
}
